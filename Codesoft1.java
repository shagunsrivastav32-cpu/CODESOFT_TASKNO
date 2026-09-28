import java.util.Scanner;
import java.util.Random;
class Codesoft1
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        Random random=new Random();

        int score=0;
        System.out.println("Welcome to the Guessing Game!");

        for (int round=1; round<=3; round++)
        {
            int number_to_guess=random.nextInt(100)+1;
            System.out.println("Round " + round);
            System.out.print("Enter your guess (1-100): ");
            System.out.println("You have 5 attempts to guess the number.");

            for (int attempt=1; attempt<=5; attempt++)
            {
                System.out.print("Enter your guess: ");
                int user_guess=sc.nextInt();

                if (user_guess==number_to_guess)
                {
                    System.out.println("Correct!");
                    score++;
                    break;
                }
                else if (user_guess<number_to_guess)
                {
                    System.out.println("Too low! Try again.");
                }
                else
                {
                    System.out.println("Too high! Try again.");
                }
                
                if (attempt==5)
             
            {
                System.out.println("Incorrect. The number was " + number_to_guess);
            }
        }
    }    
        System.out.println("Your final score is: " + score);
        sc.close();
    }

    
}
