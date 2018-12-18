package com.n26.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n26.model.StatisticsAggregate;
import com.n26.repository.StatisticsRepository;
import com.n26.util.StatisticsAggregateSerizalizer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {


    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private StatisticsRepository statisticsRepository;

    @GetMapping
    public StatisticsAggregate getStatistics(){
        return  statisticsRepository.getStatistics();
    }

}
