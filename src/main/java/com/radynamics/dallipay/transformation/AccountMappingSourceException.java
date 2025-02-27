package com.radynamics.dallipay.transformation;

public class AccountMappingSourceException extends Exception {
    public AccountMappingSourceException(String errorMessage) {
        this(errorMessage, null);
    }

    public AccountMappingSourceException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
