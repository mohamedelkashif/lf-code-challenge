package com.labforward.api.core.exception;

import org.springframework.validation.BindingResult;

import static com.labforward.api.constants.Messages.UNPROCESSEABLE_ENTIIIY;

public class EntityValidationException extends RuntimeException {

    private final transient BindingResult bindingResult;

    public EntityValidationException(BindingResult bindingResult) {
        super(UNPROCESSEABLE_ENTIIIY);
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return this.bindingResult;
    }
}
