package com.labforward.api.core.exception;

import org.springframework.validation.BindingResult;

import static com.labforward.api.constants.Messages.UNPROCESSABLE_ENTITY;

public class EntityValidationException extends RuntimeException {

    private final transient BindingResult bindingResult;

    public EntityValidationException(BindingResult bindingResult) {
        super(UNPROCESSABLE_ENTITY);
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return this.bindingResult;
    }
}
