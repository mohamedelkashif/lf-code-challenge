package com.labforward.api.common;

import com.labforward.api.hello.controller.HelloController;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = "com.labforward.api")
@WebAppConfiguration
public abstract class MVCIntegrationTest {

	protected MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webContext;

	public MVCIntegrationTest() {
	}

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webContext).apply(sharedHttpSession())
		                              .build();
	}
}
