package com.n26.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.n26.exception.SerializationException;
import com.n26.model.StatisticsAggregate;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.DecimalFormat;

@JsonComponent
public class StatisticsAggregateSerizalizer extends JsonSerializer<StatisticsAggregate> {


    @Override
    public void serialize(StatisticsAggregate statisticsAggregate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        try {
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            df.setMinimumFractionDigits(2);
            df.setGroupingUsed(false);

            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("sum", df.format(statisticsAggregate.getSum()));
            jsonGenerator.writeStringField("avg", df.format(statisticsAggregate.getAvg()));

            jsonGenerator.writeStringField("max",
                    df.format(statisticsAggregate.getMax() == null ? 0 : statisticsAggregate.getMax()));

            jsonGenerator.writeStringField("min",
                    df.format(statisticsAggregate.getMin() == null ? 0 : statisticsAggregate.getMin()));

            jsonGenerator.writeNumberField("count", statisticsAggregate.getCount());

            jsonGenerator.writeEndObject();
        }catch (Exception ex){
           throw new SerializationException();
        }

    }
}
