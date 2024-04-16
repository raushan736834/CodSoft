import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentManagementSystem extends JFrame {

    private ArrayList<Student> students = new ArrayList<>();

    public StudentManagementSystem() {
        setTitle("Student Management System");
//        setSize(400, 400);
        setBounds(450,130,500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameInput = new JTextField();
        JLabel rollLabel = new JLabel("Roll Number:");
        JTextField rollInput = new JTextField();
        JLabel gradeLabel = new JLabel("Grade:");
        JTextField gradeInput = new JTextField();

        JButton addButton = new JButton("Add Student");
        JButton displayButton = new JButton("Display Students");
        JButton backButton = new JButton("Back");
        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameInput.getText();
                String rollNumber = rollInput.getText();
                String grade = gradeInput.getText();

                if (!name.isEmpty() && !rollNumber.isEmpty() && !grade.isEmpty()) {
                    boolean rollExists = false;
                    for (Student student : students) {
                        if (student.getRollNumber().equals(rollNumber)) {
                            rollExists = true;
                            break;
                        }
                    }
                    if (!rollExists) {
                        Student student = new Student(name, rollNumber, grade);
                        students.add(student);
                        displayArea.append("Student added: " + student + "\n");
                    } else {
                        displayArea.append("Roll number already exists. Please enter a unique roll number.\n");
                    }
                } else {
                    displayArea.append("Please fill in all fields.\n");
                }
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");
                for (Student student : students) {
                    displayArea.append(student + "\n");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the JFrame
            }
        });

        panel.add(nameLabel);
        panel.add(nameInput);
        panel.add(rollLabel);
        panel.add(rollInput);
        panel.add(gradeLabel);
        panel.add(gradeInput);
        panel.add(addButton);
        panel.add(displayButton);
        panel.add(backButton);

        JScrollPane scrollPane = new JScrollPane(displayArea);
        panel.add(scrollPane);
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentManagementSystem();
            }
        });
    }
}

class Student {
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}
