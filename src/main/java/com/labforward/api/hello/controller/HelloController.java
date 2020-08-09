package com.labforward.api.hello.controller;

import com.labforward.api.core.exception.ResourceNotFoundException;
import com.labforward.api.hello.domain.Greeting;
import com.labforward.api.hello.service.HelloWorldServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/hello")
public class HelloController {

    public static final String GREETING_NOT_FOUND = "Greeting Not Found";

    private final HelloWorldServiceImpl helloWorldServiceImpl;

    @Autowired
    public HelloController(final HelloWorldServiceImpl helloWorldServiceImpl) {
        this.helloWorldServiceImpl = helloWorldServiceImpl;
    }

    @GetMapping
    public Greeting helloWorld() {
        return getHello(HelloWorldServiceImpl.DEFAULT_ID);
    }

    @GetMapping("/{id}")
    public Greeting getHello(@PathVariable String id) {
        return helloWorldServiceImpl.getGreeting(id)
                .orElseThrow(() -> new ResourceNotFoundException(GREETING_NOT_FOUND));
    }

    @PostMapping
    public Greeting createGreeting(@RequestBody Greeting request) {
        return helloWorldServiceImpl.createGreeting(request);
    }

    @PatchMapping("/{greetingId}")
    public void updateGreeting(@PathVariable String greetingId, @Valid @RequestBody Greeting greetingToBeUpdated){
        helloWorldServiceImpl.updateGreeting(greetingId, greetingToBeUpdated);
    }
}
