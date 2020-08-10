package com.labforward.api.core.exception;

import static com.labforward.api.constants.Messages.REQUIRED_PARAM;

public class BadRequestException extends RuntimeException {

	public BadRequestException() {
		super(REQUIRED_PARAM);
	}

	public BadRequestException(String message) {
		super(message);
	}
}
