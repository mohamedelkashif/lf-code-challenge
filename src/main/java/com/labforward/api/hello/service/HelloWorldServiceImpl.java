package com.labforward.api.hello.service;

import com.labforward.api.core.exception.ResourceNotFoundException;
import com.labforward.api.core.validation.EntityValidator;
import com.labforward.api.hello.domain.Greeting;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static com.labforward.api.constants.Messages.DEFAULT_ID;
import static com.labforward.api.constants.Messages.DEFAULT_MESSAGE;
import static com.labforward.api.constants.Messages.GREETING_NOT_FOUND;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {

    private Map<String, Greeting> greetings;

    private EntityValidator entityValidator;

    public HelloWorldServiceImpl(EntityValidator entityValidator) {
        this.entityValidator = entityValidator;
        this.greetings = new HashMap<>(1);
        save(getDefault());
    }

    private Greeting getDefault() {
        return new Greeting(DEFAULT_ID, DEFAULT_MESSAGE);
    }

    @Override
    public Greeting getGreeting(String greetingId) {
        Greeting greeting = this.greetings.get(greetingId);
        return Optional.ofNullable(greeting).orElseThrow(() -> new ResourceNotFoundException(GREETING_NOT_FOUND));
    }

    @Override
    public Greeting createGreeting(Greeting greeting) {
        entityValidator.validateCreate(greeting);
        greeting.setId(UUID.randomUUID().toString());
        return save(greeting);
    }

    @Override
    public Greeting updateGreeting(Greeting greetingTobeUpdated) {
        entityValidator.validateUpdate(greetingTobeUpdated);
        getGreeting(greetingTobeUpdated.getId());
        return this.save(greetingTobeUpdated);
    }

    @Override
    public Greeting getDefaultGreeting() {
        return getGreeting(DEFAULT_ID);
    }

    private Greeting save(Greeting greeting) {
        this.greetings.put(greeting.getId(), greeting);
        return greeting;
    }

}
