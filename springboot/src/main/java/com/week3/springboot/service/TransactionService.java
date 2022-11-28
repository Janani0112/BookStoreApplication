package com.week3.springboot.service;



import java.util.List;

import com.week3.springboot.model.Transaction;

public interface TransactionService {
    Transaction createTransactionBorrow(int var1, Transaction var2);

    List<Transaction> getUserTransactions(int var1);
}
