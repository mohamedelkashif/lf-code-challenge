package com.labforward.api.hello.services;

import com.labforward.api.core.exception.EntityValidationException;
import com.labforward.api.hello.domain.Greeting;
import com.labforward.api.hello.service.HelloWorldService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.labforward.api.constants.Messages.DEFAULT_ID;
import static com.labforward.api.constants.Messages.DEFAULT_MESSAGE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldServiceTest {

    private static final String HELLO_LUKE = "Hello Luke";

    @Autowired
    private HelloWorldService helloService;

    @Test
    public void shouldReturnOkForGetDefaultGreeting() {
        Greeting greeting = helloService.getDefaultGreeting();
        assertThat(greeting, is(notNullValue()));
        assertThat(DEFAULT_ID, equalTo(greeting.getId()));
        assertThat(DEFAULT_MESSAGE, equalTo(greeting.getMessage()));
    }

    @Test(expected = EntityValidationException.class)
    public void shouldThrowAnExceptionForCreateGreetingWithEmptyMessage() {
        helloService.createGreeting(new Greeting(""));
    }

    @Test(expected = EntityValidationException.class)
    public void shouldThrowAnExceptionForCreateGreetingWithNullMessage() {
        helloService.createGreeting(new Greeting(null));
    }

    @Test
    public void shouldCreateGreetingWhenValidRequestSuccessfully() {
        Greeting request = new Greeting(HELLO_LUKE);
        Greeting created = helloService.createGreeting(request);
        Assert.assertEquals(HELLO_LUKE, created.getMessage());
    }

    @Test(expected = EntityValidationException.class)
    public void shouldThrowAnExceptionForUpdateGreetingWithEmptyMessage() {
        helloService.updateGreeting(new Greeting(""));
    }

    @Test(expected = EntityValidationException.class)
    public void shouldThrowAnExceptionForUpdateGreetingWithNullMessage() {
        helloService.updateGreeting(new Greeting(null));
    }

    @Test
    public void shouldUpdateGreetingWhenValidRequestSuccessfully() {
        Greeting greeting = new Greeting(HELLO_LUKE);

        Greeting createdGreeting = helloService.createGreeting(greeting);
        assertThat(HELLO_LUKE, equalTo(createdGreeting.getMessage()));

        String id = createdGreeting.getId();
        Greeting newGreeting = new Greeting(id, "testing");
        Greeting updatedGreeting = helloService.updateGreeting(newGreeting);

        assertThat(updatedGreeting, is(notNullValue()));
        assertThat("testing", equalTo(updatedGreeting.getMessage()));
    }
}
