import java.util.Scanner;

class Student_Grade_Calculator
{
    int marks1, marks2, marks3, marks4, marks5;
    int total_marks;
    double percentage;

    void userInput()
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter marks for subject 1: ");
        marks1 = sc.nextInt();

        System.out.print("Enter marks for subject 2: ");
        marks2 = sc.nextInt();

        System.out.print("Enter marks for subject 3: ");
        marks3 = sc.nextInt();

        System.out.print("Enter marks for subject 4: ");
        marks4 = sc.nextInt();

        System.out.print("Enter marks for subject 5: ");
        marks5 = sc.nextInt();

        sc.close();
    }

    void Calculate()
    {
        total_marks = marks1 + marks2 + marks3 + marks4 + marks5;

        percentage = (total_marks / 500.0) * 100;

        if (percentage >= 90)
        {
            System.out.println("Grade: A");
        }
        else if (percentage >= 80)
        {
            System.out.println("Grade: B");
        }
        else if (percentage >= 60)
        {
            System.out.println("Grade: C");
        }
        else if (percentage >= 40)
        {
            System.out.println("Grade: D");
        }
        else
        {
            System.out.println("Grade: F");
        }
    }

    void Result()
    {
        System.out.println();
        System.out.println("Total Marks: " + total_marks);
        System.out.println("Percentage: " + percentage + "%");
    }
}

class Codesoft2
{
    public static void main(String args[])
    {
        Student_Grade_Calculator S = new Student_Grade_Calculator();

        S.userInput();
        S.Calculate();
        S.Result();
    }
}