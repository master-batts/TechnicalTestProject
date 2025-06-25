package com.technicaltest.bankingservice;

import com.technicaltest.bankingservice.exceptions.InvalidDepositException;
import com.technicaltest.bankingservice.exceptions.InvalidWithdrawalException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    @Test
    void depositPositiveAmountShouldSucceed() {
        account.deposit(1000, LocalDate.of(2023, 6, 25));
        assertEquals(1, account.getTransactions().size());
    }

    @Test
    void depositZeroOrNegativeAmountShouldThrow() {
        assertThrows(InvalidDepositException.class, () -> account.deposit(0, LocalDate.now()));
        assertThrows(InvalidDepositException.class, () -> account.deposit(-10, LocalDate.now()));
    }

    @Test
    void withdrawPositiveAmountShouldSucceed() {
        account.withdraw(500, LocalDate.of(2023, 6, 25));
        assertEquals(1, account.getTransactions().size());
    }

    @Test
    void withdrawZeroOrNegativeAmountShouldThrow() {
        assertThrows(InvalidWithdrawalException.class, () -> account.withdraw(0, LocalDate.now()));
        assertThrows(InvalidWithdrawalException.class, () -> account.withdraw(-50, LocalDate.now()));
    }
}