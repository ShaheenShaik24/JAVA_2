import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LMS{
    private JFrame frame;
    private JTextField studentNameField, rollNoField;
    private JComboBox<String> branchComboBox;
    private JRadioButton mathRadioButton, scienceRadioButton, literatureRadioButton;
    private JComboBox<String> authorComboBox;
    private JCheckBox fictionCheckBox, nonFictionCheckBox;
    private JButton submitButton;
    private JTextArea outputTextArea;

    public LMS() {
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel studentPanel = createStudentPanel();
        JPanel bookPanel = createBookPanel();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Student Details", studentPanel);
        tabbedPane.addTab("Book Details", bookPanel);

        outputTextArea = new JTextArea(10, 30);
        outputTextArea.setEditable(false);

        frame.add(tabbedPane, BorderLayout.NORTH);
        frame.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Student Name:");
        studentNameField = new JTextField();

        JLabel rollNoLabel = new JLabel("Roll No:");
        rollNoField = new JTextField();

        JLabel branchLabel = new JLabel("Branch:");
        String[] branches = {"Computer Science", "Electrical Engineering", "Mechanical Engineering"};
        branchComboBox = new JComboBox<>(branches);

        panel.add(nameLabel);
        panel.add(studentNameField);
        panel.add(rollNoLabel);
        panel.add(rollNoField);
        panel.add(branchLabel);
        panel.add(branchComboBox);

        return panel;
    }

    private JPanel createBookPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel subjectLabel = new JLabel("Subject:");
        ButtonGroup subjectGroup = new ButtonGroup();
        mathRadioButton = new JRadioButton("JAVA");
        scienceRadioButton = new JRadioButton("Computer Networks");
        literatureRadioButton = new JRadioButton("Artificial Intelligence");
        subjectGroup.add(mathRadioButton);
        subjectGroup.add(scienceRadioButton);
        subjectGroup.add(literatureRadioButton);

        JLabel authorLabel = new JLabel("Author:");
        String[] authors = {"Herbert schildt", "GrawGill", "Morgann"};
        authorComboBox = new JComboBox<>(authors);

        JLabel bookLabel = new JLabel("Books:");
        fictionCheckBox = new JCheckBox("Programming Languages");
        nonFictionCheckBox = new JCheckBox("Non-Programming languages");

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayDetails();
            }
        });

        panel.add(subjectLabel);
        panel.add(mathRadioButton);
        panel.add(new JLabel()); // Empty cell
        panel.add(scienceRadioButton);
        panel.add(new JLabel()); // Empty cell
        panel.add(literatureRadioButton);
        panel.add(authorLabel);
        panel.add(authorComboBox);
        panel.add(bookLabel);
        panel.add(fictionCheckBox);
        panel.add(new JLabel()); // Empty cell
        panel.add(nonFictionCheckBox);
        panel.add(submitButton);

        return panel;
    }

    private void displayDetails() {
        String studentName = studentNameField.getText();
        String rollNo = rollNoField.getText();
        String branch = (String) branchComboBox.getSelectedItem();

        String subject = "";
        if (mathRadioButton.isSelected()) {
            subject = "Math";
        } else if (scienceRadioButton.isSelected()) {
            subject = "Science";
        } else if (literatureRadioButton.isSelected()) {
            subject = "Literature";
        }

        String author = (String) authorComboBox.getSelectedItem();

        StringBuilder books = new StringBuilder();
        if (fictionCheckBox.isSelected()) {
            books.append("Fiction, ");
        }
        if (nonFictionCheckBox.isSelected()) {
            books.append("Non-Fiction");
        }

        String details = "Student Name: " + studentName + "\n"
                + "Roll No: " + rollNo + "\n"
                + "Branch: " + branch + "\n"
                + "Subject: " + subject + "\n"
                + "Author: " + author + "\n"
                + "Books: " + books.toString();

        outputTextArea.setText(details);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LMS());
    }
}
