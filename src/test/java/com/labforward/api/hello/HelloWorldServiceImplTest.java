package com.labforward.api.hello;

import com.labforward.api.core.exception.EntityValidationException;
import com.labforward.api.hello.domain.Greeting;
import com.labforward.api.hello.service.HelloWorldService;
import com.labforward.api.hello.service.HelloWorldServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.labforward.api.constants.Messages.DEFAULT_ID;
import static com.labforward.api.constants.Messages.DEFAULT_MESSAGE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldServiceImplTest {

	@Autowired
	private HelloWorldService helloService;

	public HelloWorldServiceImplTest() {
	}

	@Test
	public void getDefaultGreetingIsOK() {
		Greeting greeting = helloService.getDefaultGreeting();
		Assert.assertThat(greeting, is(notNullValue()));
		Assert.assertEquals(DEFAULT_ID, greeting.getId());
		Assert.assertEquals(DEFAULT_MESSAGE, greeting.getMessage());
	}

	@Test(expected = EntityValidationException.class)
	public void createGreetingWithEmptyMessageThrowsException() {
		final String EMPTY_MESSAGE = "";
		helloService.createGreeting(new Greeting(EMPTY_MESSAGE));
	}

	@Test(expected = EntityValidationException.class)
	public void createGreetingWithNullMessageThrowsException() {
		helloService.createGreeting(new Greeting(null));
	}

	@Test
	public void createGreetingOKWhenValidRequest() {
		final String HELLO_LUKE = "Hello Luke";
		Greeting request = new Greeting(HELLO_LUKE);

		Greeting created = helloService.createGreeting(request);
		Assert.assertEquals(HELLO_LUKE, created.getMessage());
	}
}
