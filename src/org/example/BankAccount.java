package org.example;

class BankAccount {
    private final String accountNumber;
    private final String bankHolderName;
    private double availableBalance;

    public BankAccount(String accountNumber, String bankHolderName, double availableBalance) {
        this.accountNumber = accountNumber;
        this.bankHolderName = bankHolderName;
        this.availableBalance = availableBalance;
    }

    public String getBankHolderName() {
        return bankHolderName;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    void displayBankAccountInformation() {
        System.out.println("Account Number: " + this.accountNumber);
        System.out.println("Holder Name: " + this.bankHolderName);
        System.out.println("Available Balance: $" + String.format("%.2f", this.availableBalance));
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.availableBalance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= this.availableBalance) {
            this.availableBalance -= amount;
            return true;
        }
        return false;
    }
}