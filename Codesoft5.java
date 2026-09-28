import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Codesoft5 extends JFrame {

    // Course class
    static class Course {
        String code;
        String title;
        String description;
        int capacity;
        int registered;
        String schedule;

        Course(String code, String title, String description,
               int capacity, String schedule) {
            this.code = code;
            this.title = title;
            this.description = description;
            this.capacity = capacity;
            this.registered = 0;
            this.schedule = schedule;
        }

        int availableSlots() {
            return capacity - registered;
        }
    }

    // Student class
    static class Student {
        String studentId;
        String name;
        ArrayList<String> registeredCourses = new ArrayList<>();

        Student(String studentId, String name) {
            this.studentId = studentId;
            this.name = name;
        }
    }

    ArrayList<Course> courses = new ArrayList<>();
    Student student;

    JTable courseTable;
    DefaultTableModel tableModel;

    JTextField studentIdField;
    JTextField studentNameField;

    JButton registerButton;
    JButton dropButton;
    JButton viewButton;
    JButton exitButton;

    // Constructor
    Codesoft5() {

        setTitle("Student Course Registration System");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addCourses();

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Title
        JLabel titleLabel = new JLabel(
                "STUDENT COURSE REGISTRATION SYSTEM",
                SwingConstants.CENTER
        );

        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Student details panel
        JPanel studentPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        studentPanel.setBorder(
                BorderFactory.createTitledBorder("Student Details")
        );

        studentPanel.add(new JLabel("Student ID:"));

        studentIdField = new JTextField();
        studentPanel.add(studentIdField);

        studentPanel.add(new JLabel("Student Name:"));

        studentNameField = new JTextField();
        studentPanel.add(studentNameField);

        // Course table
        String[] columns = {
                "Course Code",
                "Course Title",
                "Description",
                "Capacity",
                "Schedule",
                "Available Slots"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        courseTable = new JTable(tableModel);

        courseTable.setRowHeight(30);
        courseTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane = new JScrollPane(courseTable);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Available Courses")
        );

        loadCourses();

        // Buttons
        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 15, 10)
        );

        registerButton = new JButton("Register Course");
        dropButton = new JButton("Drop Course");
        viewButton = new JButton("View Registered Courses");
        exitButton = new JButton("Exit");

        buttonPanel.add(registerButton);
        buttonPanel.add(dropButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(exitButton);

        // Center panel
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));

        centerPanel.add(studentPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Button actions
        registerButton.addActionListener(e -> registerCourse());

        dropButton.addActionListener(e -> dropCourse());

        viewButton.addActionListener(e -> viewRegisteredCourses());

        exitButton.addActionListener(e -> System.exit(0));
    }

    // Add courses
    void addCourses() {

        courses.add(new Course(
                "CS101",
                "Java Programming",
                "Introduction to Java and OOP",
                5,
                "Monday 10:00 AM"
        ));

        courses.add(new Course(
                "DBMS201",
                "Database Management",
                "Database concepts and SQL",
                5,
                "Tuesday 11:00 AM"
        ));

        courses.add(new Course(
                "DSA301",
                "Data Structures",
                "Arrays, linked lists, stacks and queues",
                4,
                "Wednesday 2:00 PM"
        ));

        courses.add(new Course(
                "WEB401",
                "Web Development",
                "HTML, CSS and JavaScript",
                4,
                "Thursday 1:00 PM"
        ));

        courses.add(new Course(
                "CN501",
                "Computer Networks",
                "Networking concepts and protocols",
                3,
                "Friday 10:00 AM"
        ));
    }

    // Load courses into table
    void loadCourses() {

        tableModel.setRowCount(0);

        for (Course c : courses) {

            tableModel.addRow(new Object[]{
                    c.code,
                    c.title,
                    c.description,
                    c.capacity,
                    c.schedule,
                    c.availableSlots()
            });
        }
    }

    // Validate student details
    boolean validateStudent() {

        String id = studentIdField.getText().trim();
        String name = studentNameField.getText().trim();

        if (id.isEmpty() || name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Student ID and Student Name.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        if (student == null) {

            student = new Student(id, name);

        } else {

            student.studentId = id;
            student.name = name;
        }

        return true;
    }

    // Register course
    void registerCourse() {

        if (!validateStudent()) {
            return;
        }

        int selectedRow = courseTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a course first.",
                    "No Course Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String courseCode =
                tableModel.getValueAt(selectedRow, 0).toString();

        Course selectedCourse = findCourse(courseCode);

        if (selectedCourse == null) {
            return;
        }

        if (student.registeredCourses.contains(courseCode)) {

            JOptionPane.showMessageDialog(
                    this,
                    "You are already registered for this course.",
                    "Registration Failed",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (selectedCourse.availableSlots() <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Sorry, this course is full.",
                    "Registration Failed",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        student.registeredCourses.add(courseCode);
        selectedCourse.registered++;

        loadCourses();

        JOptionPane.showMessageDialog(
                this,
                "Registration successful!\n\n"
                        + "Course: " + selectedCourse.title
                        + "\nCourse Code: " + selectedCourse.code,
                "Registration Successful",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Drop course
    void dropCourse() {

        if (!validateStudent()) {
            return;
        }

        if (student.registeredCourses.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "You have not registered for any courses.",
                    "No Courses",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        String[] registered =
                student.registeredCourses.toArray(new String[0]);

        String selectedCode = (String) JOptionPane.showInputDialog(
                this,
                "Select the course you want to drop:",
                "Drop Course",
                JOptionPane.QUESTION_MESSAGE,
                null,
                registered,
                registered[0]
        );

        if (selectedCode == null) {
            return;
        }

        Course course = findCourse(selectedCode);

        if (course != null) {

            student.registeredCourses.remove(selectedCode);
            course.registered--;

            loadCourses();

            JOptionPane.showMessageDialog(
                    this,
                    "Course dropped successfully!\n\n"
                            + "Course: " + course.title,
                    "Course Dropped",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // View registered courses
    void viewRegisteredCourses() {

        if (!validateStudent()) {
            return;
        }

        if (student.registeredCourses.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No courses registered yet.",
                    "Registered Courses",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        StringBuilder message = new StringBuilder();

        message.append("Student ID: ")
                .append(student.studentId)
                .append("\n");

        message.append("Student Name: ")
                .append(student.name)
                .append("\n\n");

        message.append("REGISTERED COURSES\n");
        message.append("------------------------------\n");

        for (String code : student.registeredCourses) {

            Course c = findCourse(code);

            if (c != null) {

                message.append("\nCourse Code: ")
                        .append(c.code);

                message.append("\nCourse Name: ")
                        .append(c.title);

                message.append("\nSchedule: ")
                        .append(c.schedule);

                message.append("\n");
            }
        }

        JTextArea textArea = new JTextArea(
                message.toString()
        );

        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane =
                new JScrollPane(textArea);

        scrollPane.setPreferredSize(
                new Dimension(450, 300)
        );

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "Registered Courses",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Find course
    Course findCourse(String code) {

        for (Course c : courses) {

            if (c.code.equalsIgnoreCase(code)) {
                return c;
            }
        }

        return null;
    }

    // Main method
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Codesoft5 application =
                    new Codesoft5();

            application.setVisible(true);
        });
    }
}