package com.n26.data;

import com.n26.model.Transaction;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TransactionDbTest {

    private final TransactionDb db = TransactionDb.getInstance();

    @Test
    public void shouldHaveInstance(){


        assertNotNull(db);
    }

    @Test
    public void shouldAddItem(){

        db.add(new Transaction(new BigDecimal(100), new Date()));
    }

    @Test
    public void shouldAddAndRetrieveItem(){

        Date date = new Date();
        db.add(new Transaction(new BigDecimal(100), date));
        Transaction transaction = db.get(0);

        assertNotNull(transaction);
        assertEquals(date.getTime(), transaction.getTimestamp().getTime());
        assertEquals(new BigDecimal(100), transaction.getAmount());
    }
}
