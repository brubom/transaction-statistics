package com.n26.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.n26.model.Transaction;

import java.io.IOException;
import java.text.DecimalFormat;

public class TransactionSerializer extends JsonSerializer<Transaction> {
    @Override
    public void serialize(Transaction transaction, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        /*DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("sum", df.format(statisticsAggregate.getSum()));
        jsonGenerator.writeStringField("avg", df.format(statisticsAggregate.getAvg()));
        jsonGenerator.writeStringField("max", df.format(statisticsAggregate.getMax()));
        jsonGenerator.writeStringField("min", df.format(statisticsAggregate.getMin()));
        jsonGenerator.writeNumberField ("count", statisticsAggregate.getCount());

        jsonGenerator.writeEndObject();*/

    }
}
