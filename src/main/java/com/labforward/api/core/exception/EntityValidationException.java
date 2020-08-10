package com.labforward.api.core.exception;

import org.springframework.validation.BindingResult;

import static com.labforward.api.constants.Messages.BAD_REQUEST;

public class EntityValidationException extends RuntimeException {

    private final transient BindingResult bindingResult;

    public EntityValidationException(BindingResult bindingResult) {
        super(BAD_REQUEST);
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
    	return this.bindingResult;
    }
}
