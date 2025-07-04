package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        // Initialize a new bank account before each test
        account = new BankAccount("123456789", "John Doe", 1000.00);
    }

    @Test
    @DisplayName("Test creating a bank account without an initial deposit")
    void testAccountCreationWithoutDeposit() { // [cite: 10]
        BankAccount zeroBalanceAccount = new BankAccount("987654321", "Jane Smith", 0.0);
        assertEquals(0.0, zeroBalanceAccount.getAvailableBalance(), "Account should be created with zero balance if no initial deposit is made.");
    }

    @Test
    @DisplayName("Test account number getter method")
    void testGetAccountNumber() { // [cite: 9]
        assertEquals("123456789", account.getAccountNumber(), "The getAccountNumber method should return the correct account number.");
    }

    @Test
    @DisplayName("Test depositing a valid amount")
    void testDepositValidAmount() { // [cite: 5]
        account.deposit(500.00);
        assertEquals(1500.00, account.getAvailableBalance(), "The balance should increase by the deposited amount.");
    }

    @Test
    @DisplayName("Test depositing an invalid (negative) amount")
    void testDepositInvalidAmount() { // [cite: 5]
        account.deposit(-200.00);
        assertEquals(1000.00, account.getAvailableBalance(), "The balance should not change when depositing a negative amount.");
    }

    @Test
    @DisplayName("Test withdrawing a valid amount")
    void testWithdrawValidAmount() { // [cite: 6]
        boolean result = account.withdraw(300.00);
        assertTrue(result, "Withdrawal should be successful for a valid amount.");
        assertEquals(700.00, account.getAvailableBalance(), "The balance should be reduced by the withdrawn amount.");
    }

    @Test
    @DisplayName("Test withdrawing an invalid (negative) amount")
    void testWithdrawInvalidAmount() { // [cite: 7]
        boolean result = account.withdraw(-100.00);
        assertFalse(result, "Withdrawal should fail for a negative amount.");
        assertEquals(1000.00, account.getAvailableBalance(), "The balance should not change for an invalid withdrawal.");
    }

    @Test
    @DisplayName("Test withdrawing an amount that exceeds the current balance")
    void testWithdrawAmountExceedingBalance() { // [cite: 8]
        boolean result = account.withdraw(1200.00);
        assertFalse(result, "Withdrawal should fail if the amount exceeds the balance.");
        assertEquals(1000.00, account.getAvailableBalance(), "The balance should not change when withdrawal amount is greater than the balance.");
    }
}