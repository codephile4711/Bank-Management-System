package com.bankmanagement.service;

import com.bankmanagement.dao.DatabaseConnection;
import com.bankmanagement.model.Account;
import com.bankmanagement.model.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {

    private static AdminService adminService;
    private static BankService bankService;
    private static AuthService authService;

    @BeforeAll
    static void setup() {
        DatabaseConnection.initializeDatabase();
        adminService = new AdminService();
        bankService = new BankService();
        authService = new AuthService();
    }

    @Test
    void testCreateCustomerAndDeposit() {
        String username = "testuser" + System.currentTimeMillis();
        boolean created = adminService.createCustomerAccount("admin", username, "pass", "Test User", "1990-01-01", "123 Main St", "555-1234", "test@example.com", "SAVINGS", 1000.0);
        assertTrue(created);

        var user = authService.login(username, "pass");
        assertNotNull(user);

        Customer customer = bankService.getCustomerDetails(user.getId());
        assertNotNull(customer);

        var accounts = bankService.getCustomerAccounts(customer.getId());
        assertFalse(accounts.isEmpty());

        Account account = accounts.get(0);
        assertEquals(1000.0, account.getBalance());

        boolean deposited = bankService.deposit(account.getAccountId(), 500.0);
        assertTrue(deposited);

        accounts = bankService.getCustomerAccounts(customer.getId());
        assertEquals(1500.0, accounts.get(0).getBalance());
    }
}
