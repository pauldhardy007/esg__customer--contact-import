package org.winharleigh.exercise.exception;

public class CustomerAdaptorUnavailableException extends RuntimeException {

    private String errorMessage;
    private String adaptorOperation;

    public CustomerAdaptorUnavailableException(final String errorMessage, final String adaptorOperation) {
        super();
        this.errorMessage = errorMessage;
        this.adaptorOperation = adaptorOperation;
    }
}
