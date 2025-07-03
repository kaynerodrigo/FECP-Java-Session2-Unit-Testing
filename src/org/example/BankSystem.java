package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class BankSystem {

    public void addAccount(ArrayList<BankAccount> userBankAccount, String accountNumber, String holderName, double initialDeposit) {
        BankAccount newAccount = new BankAccount(accountNumber, holderName, initialDeposit);
        userBankAccount.add(newAccount);
    }

    public void displayMenu() {
        System.out.println("\n=== Bank Menu ===");
        System.out.println("1. Create account");
        System.out.println("2. View all accounts");
        System.out.println("3. Check balance");
        System.out.println("4. Deposit");
        System.out.println("5. Withdraw");
        System.out.println("6. Exit");
        System.out.print("Enter choice (1-6): ");
    }

    public static void main(String[] args) {
        String returnToMenu;
        Scanner sc = new Scanner(System.in);
        BankSystem bankSystemAccess = new BankSystem();
        ArrayList<BankAccount> userBankAccount = new ArrayList<>();

        bankSystemAccess.displayMenu();
        byte choiceNum = sc.nextByte();
        sc.nextLine(); // Consume newline

        while (choiceNum != 6) {
            // A boolean used in multiple cases, declared once here.
            boolean accountFound;

            switch (choiceNum) {
                case 1:
                    double newInitialDeposit;
                    System.out.print("Enter Account Number: ");
                    String newAccountNumber = sc.nextLine();
                    System.out.print("Enter Holder Name: ");
                    String newAccountName = sc.nextLine();
                    System.out.print("Initial deposit? (yes/no): ");
                    String newInitialDepositResponse = sc.nextLine();

                    while (!newInitialDepositResponse.equalsIgnoreCase("yes") && !newInitialDepositResponse.equalsIgnoreCase("no")) {
                        System.out.println("\nInvalid Entry! Only type yes or no.");
                        System.out.print("Initial deposit? (yes/no): ");
                        newInitialDepositResponse = sc.nextLine();
                    }

                    if (newInitialDepositResponse.equalsIgnoreCase("yes")) {
                        System.out.print("Enter initial deposit amount: ");
                        newInitialDeposit = sc.nextDouble();
                        // FIXED: The validation now re-prompts the user instead of creating an infinite loop.
                        while (newInitialDeposit <= 0) {
                            System.out.println("Enter a positive amount!");
                            System.out.print("Enter initial deposit amount: ");
                            newInitialDeposit = sc.nextDouble();
                        }
                        sc.nextLine();
                    } else {
                        newInitialDeposit = 0.0;
                    }
                    bankSystemAccess.addAccount(userBankAccount, newAccountNumber, newAccountName, newInitialDeposit);

                    System.out.println("\nAccount created successfully!");
                    break;

                case 2:
                    if (userBankAccount.isEmpty()) {
                        System.out.println("\nThere are no accounts to display.");
                    } else {
                        System.out.println("\n--- All Bank Accounts ---");
                        for (BankAccount account : userBankAccount) {
                            account.displayBankAccountInformation();
                            System.out.println("-------------------------");
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Account Number to check balance: ");
                    String accountToCheck = sc.nextLine();
                    accountFound = false;

                    if (userBankAccount.isEmpty()) {
                        System.out.println("\nThere are no accounts in the system yet.");
                    } else {
                        for (BankAccount account : userBankAccount) {
                            if (account.getAccountNumber().equals(accountToCheck)) {
                                System.out.println("\n--- Account Details ---");
                                account.displayBankAccountInformation();
                                System.out.println("-----------------------");
                                accountFound = true;
                                break;
                            }
                        }
                        if (!accountFound) {
                            System.out.println("\nAccount not found with number: " + accountToCheck);
                        }
                    }
                    break;

                case 4:
                    System.out.print("Enter Account Number to deposit into: ");
                    String depositAccountNum = sc.nextLine();
                    accountFound = false;

                    for (BankAccount account : userBankAccount) {
                        if (account.getAccountNumber().equals(depositAccountNum)) {
                            System.out.print("Enter amount to deposit: ");
                            double depositAmount = sc.nextDouble();
                            sc.nextLine();

                            if (depositAmount <= 0) {
                                System.out.println("\nInvalid amount. Deposit must be greater than zero.");
                            } else {
                                account.deposit(depositAmount);
                                System.out.println("\nDeposit successful!");
                                System.out.println("New Balance: $" + String.format("%.2f", account.getAvailableBalance()));
                            }
                            accountFound = true;
                            break;
                        }
                    }
                    if (!accountFound) {
                        System.out.println("\nAccount not found!");
                    }
                    break;

                case 5:
                    System.out.print("Enter Account Number to withdraw from: ");
                    String withdrawAccountNum = sc.nextLine();
                    accountFound = false;

                    for (BankAccount account : userBankAccount) {
                        if (account.getAccountNumber().equals(withdrawAccountNum)) {
                            System.out.print("Enter amount to withdraw: ");
                            double withdrawAmount = sc.nextDouble();
                            sc.nextLine();

                            if (account.withdraw(withdrawAmount)) {
                                System.out.println("\nWithdrawal successful!");
                                System.out.println("Remaining Balance: $" + String.format("%.2f", account.getAvailableBalance()));
                            } else {
                                System.out.println("\nWithdrawal failed. Amount must be positive and not exceed balance of $" + String.format("%.2f", account.getAvailableBalance()));
                            }
                            accountFound = true;
                            break;
                        }
                    }
                    if (!accountFound) {
                        System.out.println("\nAccount not found!");
                    }
                    break;

                case 6:
                    continue;

                default:
                    System.out.println("\nInvalid Entry! Please choose a number between 1 and 6.");
                    break;
            }


            System.out.print("\nWould you like to return to the menu? (yes/no): ");
            returnToMenu = sc.nextLine();

            while (!returnToMenu.equalsIgnoreCase("yes") && !returnToMenu.equalsIgnoreCase("no")) {
                System.out.println("\nInvalid Entry! Only type yes or no.");
                System.out.print("\nWould you like to return to the menu? (yes/no): ");
                returnToMenu = sc.nextLine();
            }

            if (returnToMenu.equalsIgnoreCase("yes")) {
                bankSystemAccess.displayMenu();
                choiceNum = sc.nextByte();
                sc.nextLine();
            } else {

                choiceNum = 6;
            }
        }

        System.out.println("\nThank you for using the Bank System. Goodbye! 👋");
        sc.close();
    }
}