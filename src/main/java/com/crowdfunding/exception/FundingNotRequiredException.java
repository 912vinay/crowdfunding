package com.crowdfunding.exception;

public class FundingNotRequiredException extends RuntimeException{

    public FundingNotRequiredException(String message) {
        super(message);
    }

    public FundingNotRequiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
