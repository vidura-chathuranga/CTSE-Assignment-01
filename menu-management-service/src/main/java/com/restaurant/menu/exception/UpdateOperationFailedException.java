package com.restaurant.menu.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UpdateOperationFailedException extends RuntimeException {

    public UpdateOperationFailedException(String message) {
        super(message);
    }
}