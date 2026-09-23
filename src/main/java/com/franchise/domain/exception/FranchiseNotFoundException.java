package com.franchise.domain.exception;

public class FranchiseNotFoundException extends RuntimeException {
    public FranchiseNotFoundException(String message) {
        super(message);
    }
}
