package com.labforward.api.hello.service;

import com.labforward.api.hello.domain.Greeting;

public interface HelloWorldService {
    Greeting getGreeting(String greetingId);

    Greeting createGreeting(Greeting greeting);

    Greeting updateGreeting(Greeting greeting);

    Greeting getDefaultGreeting();

    void deleteGreeting(String greetingId);
}
