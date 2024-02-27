package com.crowdfunding.exception;

public class FundingProcessingException extends RuntimeException{

    public FundingProcessingException(String message) {
        super(message);
    }

    public FundingProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
