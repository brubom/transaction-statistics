package com.n26.data;

import com.n26.model.Transaction;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SimpleDbTest {

    private final SimpleTransactionDb db = SimpleTransactionDb.getInstance();

    @Test
    public void shouldHaveInstance(){


        assertNotNull(db);
    }

    @Test
    public void shouldAddItem(){

        db.add(new Date(), new Transaction(new BigDecimal(100), new Date()));
    }

    @Test
    public void shouldAddAndRetrieveItem(){

        Date date = new Date();
        db.add(date, new Transaction(new BigDecimal(100), date));
        Transaction transaction = db.get(date);

        assertNotNull(transaction);
        assertEquals(date, transaction.getTimestamp());
        assertEquals(new BigDecimal(100), transaction.getAmount());
    }
}
