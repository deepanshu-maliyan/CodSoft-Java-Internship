
/*Author - Deepanshu Maliyan
CodSoft Java Internship 
TASK - 2 : ATM Interface*/

import java.util.Scanner;

class BankAccount {
  private double balance;
  private String lastTransaction;

  public BankAccount(double initialBalance) {
    this.balance = initialBalance;
    this.lastTransaction = "Initial deposit of ₹" + initialBalance;
  }

  public double getBalance() {
    return balance;
  }

  public String getLastTransaction() {
    return lastTransaction;
  }

  public void deposit(double amount) {
    if (amount > 0) {
      balance += amount;
      lastTransaction = "Deposited ₹" + amount;
      System.out.println("Deposit of ₹" + amount + " successful. Current balance: ₹" + balance);
    } else {
      System.out.println("Invalid deposit amount.");
    }
  }

  public void withdraw(double amount) {
    if (amount > 0 && amount <= balance) {
      balance -= amount;
      lastTransaction = "Withdrew ₹" + amount;
      System.out.println("Withdrawal of ₹" + amount + " successful. Current balance: ₹" + balance);
    } else {
      System.out.println("Invalid withdrawal amount or insufficient balance.");
    }
  }
}

class ATM {
  private BankAccount bankAccount;

  public ATM(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
  }

  public void displayMenu() {
    System.out.println("\nATM Menu:");
    System.out.println("1. Check Balance");
    System.out.println("2. Deposit");
    System.out.println("3. Withdraw");
    System.out.println("4. View Last Transaction");
    System.out.println("5. Exit");
  }

  public void performTransaction(int choice, Scanner scanner) {
    switch (choice) {
      case 1:
        System.out.println("Current balance: ₹" + bankAccount.getBalance());
        break;
      case 2:
        System.out.print("Enter deposit amount: ₹");
        double depositAmount = scanner.nextDouble();
        bankAccount.deposit(depositAmount);
        break;
      case 3:
        System.out.print("Enter withdrawal amount: ₹");
        double withdrawalAmount = scanner.nextDouble();
        bankAccount.withdraw(withdrawalAmount);
        break;
      case 4:
        System.out.println("Last transaction: " + bankAccount.getLastTransaction());
        break;
      case 5:
        System.out.println("Exiting ATM. Thank you!");
        scanner.close();
        System.exit(0);
      default:
        System.out.println("Invalid choice. Please select a valid option.");
    }
  }
}

public class Atm {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter initial account balance: ₹");
    double initialBalance = scanner.nextDouble();
    BankAccount bankAccount = new BankAccount(initialBalance);
    ATM atm = new ATM(bankAccount);
    while (true) {
      atm.displayMenu();
      System.out.print("Select an option: ");
      int choice = scanner.nextInt();
      atm.performTransaction(choice, scanner);
    }
  }
}
