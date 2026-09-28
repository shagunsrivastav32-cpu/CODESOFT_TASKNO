import java.util.Scanner;

class BankAccount {
    private double balance;

    BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    void deposit(double amount) {
        balance = balance + amount;
    }

    boolean withdraw(double amount) {
        if (amount <= balance) {
            balance = balance - amount;
            return true;
        }
        return false;
    }

    double getBalance() {
        return balance;
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    ATM(BankAccount account) {
        this.account = account;
        scanner = new Scanner(System.in);
    }

    void start() {
        int choice;

        do {
            System.out.println();
            System.out.println("====================================");
            System.out.println("          WELCOME TO ATM");
            System.out.println("====================================");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.println("====================================");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;

                case 2:
                    depositMoney();
                    break;

                case 3:
                    withdrawMoney();
                    break;

                case 4:
                    System.out.println();
                    System.out.println("====================================");
                    System.out.println("     THANK YOU FOR USING OUR ATM");
                    System.out.println("====================================");
                    break;

                default:
                    System.out.println("Invalid choice!");
                    System.out.println("Please select a number from 1 to 4.");
            }

        } while (choice != 4);

        scanner.close();
    }

    void checkBalance() {
        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("          ACCOUNT BALANCE");
        System.out.println("------------------------------------");
        System.out.printf("Available Balance: Rs. %.2f%n",
                account.getBalance());
        System.out.println("------------------------------------");
    }

    void depositMoney() {
        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("           DEPOSIT MONEY");
        System.out.println("------------------------------------");

        System.out.print("Enter amount: Rs. ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        account.deposit(amount);

        System.out.println("Deposit successful!");
        System.out.printf("Deposited Amount: Rs. %.2f%n", amount);
        System.out.printf("New Balance: Rs. %.2f%n",
                account.getBalance());

        System.out.println("------------------------------------");
    }

    void withdrawMoney() {
        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("          WITHDRAW MONEY");
        System.out.println("------------------------------------");

        System.out.print("Enter amount: Rs. ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful!");
            System.out.printf("Withdrawn Amount: Rs. %.2f%n", amount);
            System.out.printf("Remaining Balance: Rs. %.2f%n",
                    account.getBalance());
        } else {
            System.out.println("Transaction failed!");
            System.out.println("Insufficient balance.");
            System.out.printf("Available Balance: Rs. %.2f%n",
                    account.getBalance());
        }

        System.out.println("------------------------------------");
    }
}

public class Codesoft3 {
    public static void main(String[] args) {

        BankAccount account = new BankAccount(10000.00);

        ATM atm = new ATM(account);

        atm.start();
    }
}