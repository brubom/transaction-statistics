package com.n26.repository;

import com.n26.data.SimpleTransactionDb;
import com.n26.model.Transaction;


public class TransactionRepository {

    private final SimpleTransactionDb db = SimpleTransactionDb.getInstance();



    public Long countAll() {

        return db.count();
    }



    public Transaction create(Transaction transaction) {

        db.add(transaction.getTimestamp(), transaction);
        return db.get(transaction.getTimestamp());
    }
}
