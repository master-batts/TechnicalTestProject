package com.technicaltest.bankingservice;

import com.technicaltest.bankingservice.exceptions.InvalidDepositException;
import com.technicaltest.bankingservice.exceptions.InvalidWithdrawalException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



public class Account implements AccountService {
    private final List<Transaction> transactions = new ArrayList<>();

    public void deposit(int amount, LocalDate date) {
        if (amount <= 0) throw new InvalidDepositException("Deposit must be positive");
        transactions.add(new Transaction(date, amount));
    }

    public void withdraw(int amount, LocalDate date) {
        if (amount <= 0) throw new InvalidWithdrawalException("Withdrawal must be positive");
        transactions.add(new Transaction(date, -amount));
    }

    @Override
    public void deposit(int amount) {
        throw new UnsupportedOperationException("Use deposit(amount, date) instead.");
    }

    @Override
    public void withdraw(int amount) {
        throw new UnsupportedOperationException("Use withdraw(amount, date) instead.");
    }

    @Override
    public void printStatement() {
        System.out.println("Date       || Amount || Balance");
        int balance = 0;
        List<Transaction> sorted = new ArrayList<>(transactions);
        // lambas here to  avoid multiple lines of code
        sorted.sort((t1, t2) -> t1.getDate().compareTo(t2.getDate()));
        List<String> lines = new ArrayList<>();
        for (Transaction t : sorted) {
            balance += t.getAmount();
            lines.add(String.format("%s || %d   || %d",
                    t.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    t.getAmount(),
                    balance));
        }
        for (int i = lines.size() - 1; i >= 0; i--) {
            System.out.println(lines.get(i));
        }
    }
    // added to for unit test
    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }
}