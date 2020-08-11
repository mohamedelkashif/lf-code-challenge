package com.labforward.api.hello.controller;

import com.labforward.api.common.MVCIntegrationTest;
import com.labforward.api.core.exception.ResourceNotFoundException;
import com.labforward.api.hello.domain.Greeting;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static com.labforward.api.constants.Messages.DEFAULT_ID;
import static com.labforward.api.constants.Messages.DEFAULT_MESSAGE;
import static com.labforward.api.constants.Messages.UNPROCESSEABLE_ENTIIIY;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTest extends MVCIntegrationTest {

    private static final String RESOURCE_URL = "/hello";

    private static final String HELLO_LUKE = "Hello Luke";

    @Test
    public void getHelloIsOKAndReturnsValidJSON() throws Exception {
        mockMvc.perform(get(RESOURCE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(DEFAULT_ID)))
                .andExpect(jsonPath("$.message", is(DEFAULT_MESSAGE)));
    }

    @Test
    public void createShouldReturnUnprocessedEntityWhenMessageMissing() throws Exception {
        String body = "{}";
        mockMvc.perform(post(RESOURCE_URL).content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.validationErrors", hasSize(1)))
                .andExpect(jsonPath("$.validationErrors[*].field", contains("message")));
    }

    @Test
    public void createShouldReturnsUnprocessedEntityWhenUnexpectedAttributeProvided() throws Exception {
        final String body = "{ \"tacos\":\"value\" }}";
        mockMvc.perform(post(RESOURCE_URL).content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", containsString(UNPROCESSEABLE_ENTIIIY)));
    }

    @Test
    public void createShouldReturnsUnprocessedEntityWhenMessageEmptyString() throws Exception {
        Greeting emptyMessage = new Greeting("");
        final String body = getGreetingBody(emptyMessage);

        mockMvc.perform(post(RESOURCE_URL).content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.validationErrors", hasSize(1)))
                .andExpect(jsonPath("$.validationErrors[*].field", contains("message")));
    }

    @Test
    public void createShouldReturnOkWhenRequiredGreetingProvided() throws Exception {
        Greeting hello = new Greeting(HELLO_LUKE);
        final String body = getGreetingBody(hello);

        mockMvc.perform(post(RESOURCE_URL).contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(hello.getMessage())));
    }

    @Test
    public void updateShouldReturnOkWhenRequiredGreetingProvided() throws Exception {
        String id = UUID.randomUUID().toString();
        Greeting greeting = new Greeting(id, HELLO_LUKE);
        Greeting updatedGreeting = new Greeting(id, "testing");

        final String jsonStringBody = getGreetingBody(greeting);
        final String jsonStringBodyToBeSent = getGreetingBody(updatedGreeting);

        mockMvc.perform(post(RESOURCE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStringBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(greeting.getMessage())));


        mockMvc.perform(patch(RESOURCE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStringBodyToBeSent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(updatedGreeting.getMessage())));
    }

    @Test
    public void updateShouldThrowExceptionWhenIdIsNotFound() throws Exception {
        String id = UUID.randomUUID().toString();
        Greeting greeting = new Greeting(id, HELLO_LUKE);
        final String jsonStringBody = getGreetingBody(greeting);

        mockMvc.perform(patch(RESOURCE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStringBody))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException));

    }

    @Test
    public void updateShouldReturnsUnprocessedEntityWhenMessageEmptyString() throws Exception {
        Greeting emptyMessage = new Greeting("");
        final String body = getGreetingBody(emptyMessage);

        mockMvc.perform(patch(RESOURCE_URL).content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.validationErrors", hasSize(1)))
                .andExpect(jsonPath("$.validationErrors[*].field", contains("message")));
    }

    @Test
    public void updateShouldReturnsUnprocessedEntityWhenUnexpectedAttributeProvided() throws Exception {
        final String body = "{ \"tacos\":\"value\" }}";
        mockMvc.perform(patch(RESOURCE_URL)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", containsString(UNPROCESSEABLE_ENTIIIY)));
    }

    @Test
    public void updateShouldReturnsUnprocessedEntityWhenMessageMissing() throws Exception {
        String body = "{}";
        mockMvc.perform(patch(RESOURCE_URL).content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.validationErrors", hasSize(1)))
                .andExpect(jsonPath("$.validationErrors[*].field", contains("message")));
    }


    private String getGreetingBody(Greeting greeting) throws JSONException {
        JSONObject json = new JSONObject().put("message", greeting.getMessage());

        if (greeting.getId() != null) {
            json.put("id", greeting.getId());
        }
        return json.toString();
    }
}
