package com.technicaltest.bankingservice;

public interface AccountService {
    void deposit(int amount);
    void withdraw(int amount);
    void printStatement();
}
