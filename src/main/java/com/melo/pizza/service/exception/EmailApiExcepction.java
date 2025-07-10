package com.melo.pizza.service.exception;

public class EmailApiExcepction extends RuntimeException{

    public EmailApiExcepction(){
        super("error sending email");
    }
}
