package com.labforward.api.core.domain;

import java.util.Date;

/**
 * Base message response
 */
public class ApiMessage {

    protected String message;

    protected String[] errors;

    protected Date timestamp;

    public ApiMessage(String message) {
        this.message = message;
    }

    public ApiMessage(String message, Date timestamp) {
        this.message = message;
        this.errors = new String[0];
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

	public String[] getErrors() {
		return errors;
	}
}
