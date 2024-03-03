package org.esg.exercise.exception;

import lombok.Getter;

@Getter
public class CustomerAdaptorUnavailableException extends RuntimeException {

    private final String errorMessage;
    private final String adaptorOperation;

    public CustomerAdaptorUnavailableException(final String errorMessage, final String adaptorOperation) {
        super();
        this.errorMessage = errorMessage;
        this.adaptorOperation = adaptorOperation;
    }
}
