package com.week3.springboot.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.week3.springboot.Exception.NoObjectFoundException;
import com.week3.springboot.model.Transaction;
import com.week3.springboot.service.TransactionService;

@EnableTransactionManagement
@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    public TransactionController() {
    }

    @PostMapping({"/borrow/{classId}"})
    private ResponseEntity<Transaction> borrowBook(@PathVariable int classId, @RequestBody Transaction transaction) {
        try {
            System.out.println(transaction);
            return ResponseEntity.ok().body(this.transactionService.createTransactionBorrow(classId, transaction));
        } catch (Exception var4) {
            throw new NoObjectFoundException("Input is not correct!");
        }
    }

    @GetMapping({"/transaction/{userId}"})
    private ResponseEntity<List<Transaction>> getTransactions(@PathVariable int userId) {
        return ResponseEntity.ok().body(this.transactionService.getUserTransactions(userId));
    }
}
