package com.n26.rest;

import com.n26.exception.DeserializationException;
import com.n26.exception.SerializationException;
import com.n26.model.Transaction;
import com.n26.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Inject
    private TransactionRepository transactionRepository;

    private static final int SECONDS_STAT = 60;

    @RequestMapping

    public void addTransaction(@RequestBody Transaction transaction, HttpServletResponse response){

        if((System.currentTimeMillis() - transaction.getTimestamp()) / 1000 >= SECONDS_STAT ){

            response.setStatus(HttpStatus.NO_CONTENT.value());
            return;

        }

        if(transaction.getTimestamp() > System.currentTimeMillis()){
            response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            return;
        }

        transactionRepository.create(transaction);
        response.setStatus(HttpStatus.CREATED.value());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(){

        transactionRepository.clear();
    }

    @ExceptionHandler(SerializationException.class)
    public void serializationErrorHandler(HttpServletResponse response) {
        response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }


    @ExceptionHandler(DeserializationException.class)
    public void deserializationErrorHandler(DeserializationException error, HttpServletResponse response) {

        if(error.getErrorCode() == DeserializationException.ErrorCodes.INVALID_JSON)
            response.setStatus(HttpStatus.BAD_REQUEST.value());

        if(error.getErrorCode() == DeserializationException.ErrorCodes.INVALID_FIELD_VALUE)
            response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

}
