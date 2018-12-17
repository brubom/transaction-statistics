package com.n26.data;

import com.n26.model.Transaction;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * @author brunomoreira
 * NOT a real db, of course, just a quick singleton implementation. Although it says
 * on exercise that this should be a prod ready code, using a nosql database or at least a
 * caching mechanism is a must.
 */
public class SimpleTransactionDb extends LinkedHashMap<Date, Transaction> {

    private static final SimpleTransactionDb INSTANCE = new SimpleTransactionDb();
    public static SimpleTransactionDb getInstance() {
        return INSTANCE;
    }

    private HashMap<Date, Transaction> HashMap = new HashMap<>();

    public void add(Date date, Transaction transaction){
        HashMap.put(date, transaction);
    }

    public void clear(){

        HashMap.clear();
    }

    public Transaction get(Date date){
        return HashMap.get(date);
    }

   public Long count(){
        return Long.valueOf(HashMap.size());
   }
   
   public HashMap<Date, Transaction> getAllTransactions(){
        return this.HashMap;
   }


    protected boolean removeEldestEntry(Map.Entry eldest) {

        LocalDateTime currentTime = LocalDateTime.now();

        LocalDateTime eldestTime = Instant.ofEpochMilli(((Date) eldest.getKey()).getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return SECONDS.between(currentTime, eldestTime) > 60;
    }
}
