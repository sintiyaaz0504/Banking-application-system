import java.util.ArrayList;
import java.util.Scanner;

// Account class (data holder)
class Account {
    private static int nextAccountNumber = 1001;
    private int accountNumber;
    private String name;
    private double balance;

    public Account(String name) {
        this.accountNumber = nextAccountNumber++;
        this.name = name;
        this.balance = 0.0;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive!");
        } else {
            balance += amount;
            System.out.println("Deposited ₹" + amount + " successfully.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive!");
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            System.out.println("Withdrawn ₹" + amount + " successfully.");
        }
    }

    public void displayBalance() {
        System.out.println("Account Number: " + accountNumber + " | Name: " + name + " | Balance: ₹" + balance);
    }
}

// Main banking app class with menu
public class BankingApp {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n--- Simple Banking Application ---");
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        depositMoney();
                        break;
                    case 3:
                        withdrawMoney();
                        break;
                    case 4:
                        checkBalance();
                        break;
                    case 5:
                        System.out.println("Thank you for using the banking application!");
                        break;
                    default:
                        System.out.println("Invalid choice! Please select between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                choice = 0;
            }

        } while (choice != 5);
    }

    private static void createAccount() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        Account newAccount = new Account(name);
        accounts.add(newAccount);
        System.out.println("Account created successfully!");
        System.out.println("Your Account Number is: " + newAccount.getAccountNumber());
    }

    private static Account findAccount(int accNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNumber) {
                return acc;
            }
        }
        return null;
    }

    private static void depositMoney() {
        System.out.print("Enter account number: ");
        int accNumber = Integer.parseInt(sc.nextLine());
        Account acc = findAccount(accNumber);
        if (acc != null) {
            System.out.print("Enter amount to deposit: ₹");
            double amount = Double.parseDouble(sc.nextLine());
            acc.deposit(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void withdrawMoney() {
        System.out.print("Enter account number: ");
        int accNumber = Integer.parseInt(sc.nextLine());
        Account acc = findAccount(accNumber);
        if (acc != null) {
            System.out.print("Enter amount to withdraw: ₹");
            double amount = Double.parseDouble(sc.nextLine());
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void checkBalance() {
        System.out.print("Enter account number: ");
        int accNumber = Integer.parseInt(sc.nextLine());
        Account acc = findAccount(accNumber);
        if (acc != null) {
            acc.displayBalance();
        } else {
            System.out.println("Account not found!");
        }
    }
}
