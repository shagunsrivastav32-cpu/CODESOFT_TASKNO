import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Codesoft4 extends JFrame {

    // Quiz Questions
    String[] questions = {
        "Which language is primarily used for Android development?",
        "Which of the following is NOT an OOP concept?",
        "What does SQL stand for?",
        "Which data structure follows FIFO?",
        "Which keyword is used to inherit a class in Java?"
    };

    // Options for each question
    String[][] options = {
        {"Java", "HTML", "CSS", "SQL"},
        {"Inheritance", "Polymorphism", "Compilation", "Encapsulation"},
        {"Structured Query Language",
         "Simple Query Language",
         "System Query Language",
         "Sequential Query Language"},
        {"Stack", "Queue", "Tree", "Graph"},
        {"this", "super", "extends", "implements"}
    };

    // Correct answer index
    int[] correctAnswers = {0, 2, 0, 1, 2};

    // Quiz variables
    int currentQuestion = 0;
    int score = 0;
    int correctCount = 0;
    int incorrectCount = 0;

    // Timer
    int timeLeft = 10;
    Timer timer;

    // GUI Components
    JLabel questionNumberLabel;
    JLabel questionLabel;
    JLabel timerLabel;
    JLabel scoreLabel;

    JRadioButton option1;
    JRadioButton option2;
    JRadioButton option3;
    JRadioButton option4;

    ButtonGroup optionGroup;

    JButton nextButton;


    // Constructor
    public Codesoft4() {

        setTitle("Quiz Master - Java Quiz Application");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createWelcomeScreen();
    }


    // Welcome Screen
    private void createWelcomeScreen() {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(20, 20));

        panel.setBorder(
            BorderFactory.createEmptyBorder(
                50, 50, 50, 50
            )
        );

        JLabel title = new JLabel(
            "QUIZ MASTER",
            SwingConstants.CENTER
        );

        title.setFont(
            new Font("Arial", Font.BOLD, 32)
        );


        JLabel subtitle = new JLabel(
            "<html><center>"
            + "Test your knowledge with our timed quiz!<br>"
            + "Each question has 10 seconds."
            + "</center></html>",
            SwingConstants.CENTER
        );

        subtitle.setFont(
            new Font("Arial", Font.PLAIN, 18)
        );


        JButton startButton = new JButton(
            "START QUIZ"
        );

        startButton.setFont(
            new Font("Arial", Font.BOLD, 18)
        );

        startButton.addActionListener(
            e -> startQuiz()
        );


        panel.add(
            title,
            BorderLayout.NORTH
        );

        panel.add(
            subtitle,
            BorderLayout.CENTER
        );

        panel.add(
            startButton,
            BorderLayout.SOUTH
        );

        add(panel);
    }


    // Start Quiz
    private void startQuiz() {

        getContentPane().removeAll();

        JPanel mainPanel = new JPanel(
            new BorderLayout(15, 15)
        );

        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(
                25, 35, 25, 35
            )
        );


        // Top section
        JPanel topPanel = new JPanel(
            new BorderLayout()
        );


        questionNumberLabel = new JLabel();

        questionNumberLabel.setFont(
            new Font("Arial", Font.BOLD, 16)
        );


        timerLabel = new JLabel();

        timerLabel.setFont(
            new Font("Arial", Font.BOLD, 16)
        );


        scoreLabel = new JLabel();

        scoreLabel.setFont(
            new Font("Arial", Font.BOLD, 16)
        );


        topPanel.add(
            questionNumberLabel,
            BorderLayout.WEST
        );

        topPanel.add(
            timerLabel,
            BorderLayout.CENTER
        );

        topPanel.add(
            scoreLabel,
            BorderLayout.EAST
        );


        // Question section
        questionLabel = new JLabel();

        questionLabel.setFont(
            new Font("Arial", Font.BOLD, 20)
        );


        JPanel questionPanel = new JPanel(
            new BorderLayout()
        );

        questionPanel.setBorder(
            BorderFactory.createEmptyBorder(
                20, 10, 10, 10
            )
        );


        questionPanel.add(
            questionLabel,
            BorderLayout.NORTH
        );


        // Radio buttons
        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();


        option1.setFont(
            new Font("Arial", Font.PLAIN, 16)
        );

        option2.setFont(
            new Font("Arial", Font.PLAIN, 16)
        );

        option3.setFont(
            new Font("Arial", Font.PLAIN, 16)
        );

        option4.setFont(
            new Font("Arial", Font.PLAIN, 16)
        );


        optionGroup = new ButtonGroup();

        optionGroup.add(option1);
        optionGroup.add(option2);
        optionGroup.add(option3);
        optionGroup.add(option4);


        JPanel optionsPanel = new JPanel();

        optionsPanel.setLayout(
            new BoxLayout(
                optionsPanel,
                BoxLayout.Y_AXIS
            )
        );


        optionsPanel.add(option1);

        optionsPanel.add(
            Box.createVerticalStrut(15)
        );

        optionsPanel.add(option2);

        optionsPanel.add(
            Box.createVerticalStrut(15)
        );

        optionsPanel.add(option3);

        optionsPanel.add(
            Box.createVerticalStrut(15)
        );

        optionsPanel.add(option4);


        questionPanel.add(
            optionsPanel,
            BorderLayout.CENTER
        );


        // Submit button
        nextButton = new JButton(
            "SUBMIT & NEXT"
        );

        nextButton.setFont(
            new Font("Arial", Font.BOLD, 16)
        );


        nextButton.addActionListener(
            e -> submitAnswer()
        );


        JPanel bottomPanel = new JPanel(
            new FlowLayout(
                FlowLayout.RIGHT
            )
        );

        bottomPanel.add(nextButton);


        mainPanel.add(
            topPanel,
            BorderLayout.NORTH
        );

        mainPanel.add(
            questionPanel,
            BorderLayout.CENTER
        );

        mainPanel.add(
            bottomPanel,
            BorderLayout.SOUTH
        );


        add(mainPanel);


        displayQuestion();

        startTimer();


        revalidate();
        repaint();
    }


    // Display Question
    private void displayQuestion() {

        questionNumberLabel.setText(
            "Question "
            + (currentQuestion + 1)
            + " / "
            + questions.length
        );


        questionLabel.setText(
            "<html>"
            + questions[currentQuestion]
            + "</html>"
        );


        option1.setText(
            options[currentQuestion][0]
        );

        option2.setText(
            options[currentQuestion][1]
        );

        option3.setText(
            options[currentQuestion][2]
        );

        option4.setText(
            options[currentQuestion][3]
        );


        optionGroup.clearSelection();


        scoreLabel.setText(
            "Score: " + score
        );


        timeLeft = 10;


        timerLabel.setText(
            "Time: "
            + timeLeft
            + " sec"
        );
    }


    // Start Timer
    private void startTimer() {

        if (timer != null) {
            timer.stop();
        }


        timeLeft = 10;


        timer = new Timer(
            1000,
            new ActionListener() {

                @Override
                public void actionPerformed(
                    ActionEvent e
                ) {

                    timeLeft--;


                    timerLabel.setText(
                        "Time: "
                        + timeLeft
                        + " sec"
                    );


                    if (timeLeft <= 0) {

                        timer.stop();


                        JOptionPane.showMessageDialog(
                            Codesoft4.this,
                            "Time's up!",
                            "Time Up",
                            JOptionPane.WARNING_MESSAGE
                        );


                        incorrectCount++;

                        moveToNextQuestion();
                    }
                }
            }
        );


        timer.start();
    }


    // Submit Answer
    private void submitAnswer() {

        // Check if user selected an option
        if (!option1.isSelected()
            && !option2.isSelected()
            && !option3.isSelected()
            && !option4.isSelected()) {

            JOptionPane.showMessageDialog(
                this,
                "Please select an answer.",
                "No Answer",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        timer.stop();


        int selectedAnswer = -1;


        if (option1.isSelected()) {

            selectedAnswer = 0;

        } else if (option2.isSelected()) {

            selectedAnswer = 1;

        } else if (option3.isSelected()) {

            selectedAnswer = 2;

        } else if (option4.isSelected()) {

            selectedAnswer = 3;
        }


        // Check answer
        if (selectedAnswer
            == correctAnswers[currentQuestion]) {

            score++;

            correctCount++;


            JOptionPane.showMessageDialog(
                this,
                "Correct Answer!",
                "Result",
                JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            incorrectCount++;


            JOptionPane.showMessageDialog(
                this,
                "Incorrect Answer!",
                "Result",
                JOptionPane.ERROR_MESSAGE
            );
        }


        moveToNextQuestion();
    }


    // Move to next question
    private void moveToNextQuestion() {

        currentQuestion++;


        if (currentQuestion < questions.length) {

            displayQuestion();

            startTimer();

        } else {

            showResult();
        }
    }


    // Result Screen
    private void showResult() {

        if (timer != null) {
            timer.stop();
        }


        getContentPane().removeAll();


        JPanel resultPanel = new JPanel(
            new BorderLayout(20, 20)
        );


        resultPanel.setBorder(
            BorderFactory.createEmptyBorder(
                50, 50, 50, 50
            )
        );


        JLabel title = new JLabel(
            "QUIZ COMPLETED!",
            SwingConstants.CENTER
        );


        title.setFont(
            new Font("Arial", Font.BOLD, 30)
        );


        int totalQuestions = questions.length;


        double percentage =
            ((double) score / totalQuestions) * 100;


        JLabel result = new JLabel(
            "<html><center>"
            + "Final Score: "
            + score
            + " / "
            + totalQuestions
            + "<br><br>"
            + "Percentage: "
            + String.format(
                "%.2f",
                percentage
            )
            + "%"
            + "<br><br>"
            + "Correct Answers: "
            + correctCount
            + "<br>"
            + "Incorrect Answers: "
            + incorrectCount
            + "</center></html>",
            SwingConstants.CENTER
        );


        result.setFont(
            new Font("Arial", Font.PLAIN, 20)
        );


        JButton exitButton = new JButton(
            "EXIT"
        );


        exitButton.setFont(
            new Font("Arial", Font.BOLD, 16)
        );


        exitButton.addActionListener(
            e -> System.exit(0)
        );


        resultPanel.add(
            title,
            BorderLayout.NORTH
        );

        resultPanel.add(
            result,
            BorderLayout.CENTER
        );

        resultPanel.add(
            exitButton,
            BorderLayout.SOUTH
        );


        add(resultPanel);


        revalidate();
        repaint();
    }


    // Main Method
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Codesoft4 quiz =
                new Codesoft4();

            quiz.setVisible(true);
        });
    }
}