package com.labforward.api.core.exception;

import static com.labforward.api.constants.Messages.USER_AGENT_REQUIRED;

public class UserAgentRequiredException extends RuntimeException {

	public UserAgentRequiredException() {
		super(USER_AGENT_REQUIRED);
	}
}
