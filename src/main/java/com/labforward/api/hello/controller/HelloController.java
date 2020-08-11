package com.labforward.api.hello.controller;

import com.labforward.api.hello.domain.Greeting;
import com.labforward.api.hello.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.labforward.api.constants.Messages.DEFAULT_ID;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private final HelloWorldService helloWorldService;

    @Autowired
    public HelloController(final HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping
    public Greeting helloWorld() {
        return getHello(DEFAULT_ID);
    }

    @GetMapping("/{id}")
    public Greeting getHello(@PathVariable String id) {
        return helloWorldService.getGreeting(id);
    }

    @PostMapping
    public Greeting createGreeting(@RequestBody Greeting request) {
        return helloWorldService.createGreeting(request);
    }

    @PatchMapping
    public Greeting updateGreeting(@RequestBody Greeting greetingToBeUpdated) {
        return helloWorldService.updateGreeting(greetingToBeUpdated);
    }
}
