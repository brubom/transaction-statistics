package com.n26.data;

import com.n26.model.Transaction;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.time.temporal.ChronoUnit.SECONDS;

public class MemoryCache {

    private final LinkedHashMap<Date, Transaction> map =
            new LinkedHashMap<>();

    private static final int CACHE_TTL = 60;

    public MemoryCache() {

        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(CACHE_TTL * 1000);
                } catch (InterruptedException ex) {
                }
                cleanup();
            }
        });

        t.setDaemon(true);
        t.start();

    }

    public void cleanup() {


        synchronized (map) {

            while (true) {
                Map.Entry<Date, Transaction> entry = map.entrySet().iterator().next();

                if (entry == null) {
                    break;
                }

                LocalDateTime currentTime = LocalDateTime.now();

                LocalDateTime eldestTime = Instant.ofEpochMilli(entry.getKey().getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();

                if (SECONDS.between(currentTime, eldestTime) > CACHE_TTL) {
                    map.remove(entry.getKey());
                } else {
                    break;
                }

                Thread.yield();

            }
        }

    }
}
