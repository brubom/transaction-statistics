package com.n26.repository;

import com.n26.data.TransactionDb;
import com.n26.model.Transaction;

import javax.inject.Inject;


public class TransactionRepository {

    @Inject
    private TransactionDb transactionDb;



    public Transaction create(Transaction transaction) {

        transactionDb.add(transaction);
        return transaction;
    }

    public void clear() {
        transactionDb.clear();
    }
}
