import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LMS{
    private JFrame frame;
    private JTextField studentNameField, rollNoField;
    private JComboBox<String> branchComboBox;
    private JRadioButton sub1RadioButton, sub2RadioButton, sub3RadioButton;
    private JComboBox<String> authorComboBox;
    private JCheckBox plCheckBox, nonplCheckBox;
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
        sub1RadioButton = new JRadioButton("JAVA");
        sub2RadioButton = new JRadioButton("Computer Networks");
        sub3RadioButton = new JRadioButton("Artificial Intelligence");
        subjectGroup.add(sub1RadioButton);
        subjectGroup.add(sub2RadioButton);
        subjectGroup.add(sub3RadioButton);

        JLabel authorLabel = new JLabel("Author:");
        String[] authors = {"Herbert schildt", "GrawGill", "Morgann"};
        authorComboBox = new JComboBox<>(authors);

        JLabel bookLabel = new JLabel("Books:");
        plCheckBox = new JCheckBox("Programming Languages");
        nonplCheckBox = new JCheckBox("Non-Programming languages");

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayDetails();
            }
        });

        panel.add(subjectLabel);
        panel.add(sub1RadioButton);
        panel.add(new JLabel()); // Empty cell
        panel.add(sub2RadioButton);
        panel.add(new JLabel()); // Empty cell
        panel.add(sub3RadioButton);
        panel.add(authorLabel);
        panel.add(authorComboBox);
        panel.add(bookLabel);
        panel.add(plCheckBox);
        panel.add(new JLabel()); // Empty cell
        panel.add(nonplCheckBox);
        panel.add(submitButton);

        return panel;
    }

    private void displayDetails() {
        String studentName = studentNameField.getText();
        String rollNo = rollNoField.getText();
        String branch = (String) branchComboBox.getSelectedItem();

        String subject = "";
        if (sub1RadioButton.isSelected()) {
            subject = "Java";
        } else if (sub2RadioButton.isSelected()) {
            subject = "Computer Networks";
        } else if (sub3RadioButton.isSelected()) {
            subject = "Artificial Intelligence";
        }

        String author = (String) authorComboBox.getSelectedItem();

        StringBuilder books = new StringBuilder();
        if (plCheckBox.isSelected()) {
            books.append("Programming Languages,");
        }
        if (nonplCheckBox.isSelected()) {
            books.append("Non-Programming Languages");
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
