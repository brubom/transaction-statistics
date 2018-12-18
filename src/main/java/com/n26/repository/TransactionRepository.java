package com.n26.repository;

import com.n26.data.TransactionDb;
import com.n26.model.Transaction;


public class TransactionRepository {

    private final TransactionDb db = TransactionDb.getInstance();



    public Long countAll() {

        return db.count();
    }



    public Transaction create(Transaction transaction) {

        db.add(transaction);
        return transaction;
    }
}
