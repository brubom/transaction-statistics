package com.n26;

import com.n26.repository.StatisticsRepository;
import com.n26.repository.TransactionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.inject.Inject;

@SpringBootApplication
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public TransactionRepository transactionRepository(){
        return new TransactionRepository();
    }

    @Bean
    public StatisticsRepository statisticsRepository(){
        return  new StatisticsRepository();
    }
}
