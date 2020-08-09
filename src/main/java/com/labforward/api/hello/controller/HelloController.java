package com.labforward.api.hello.controller;

import com.labforward.api.core.exception.ResourceNotFoundException;
import com.labforward.api.hello.domain.Greeting;
import com.labforward.api.hello.service.HelloWorldService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    public static final String GREETING_NOT_FOUND = "Greeting Not Found";

    private HelloWorldService helloWorldService;

    public HelloController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping
    public Greeting helloWorld() {
        return getHello(HelloWorldService.DEFAULT_ID);
    }

    @GetMapping("/{id}")
    public Greeting getHello(@PathVariable String id) {
        return helloWorldService.getGreeting(id)
                .orElseThrow(() -> new ResourceNotFoundException(GREETING_NOT_FOUND));
    }

    @PostMapping
    public Greeting createGreeting(@RequestBody Greeting request) {
        return helloWorldService.createGreeting(request);
    }
}
