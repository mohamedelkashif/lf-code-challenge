package com.labforward.api.integrationTests;

import com.labforward.api.hello.domain.Greeting;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.net.URL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LabforwardIntgerationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private URL resourceURL;

    @BeforeAll
    public void setUp() throws Exception {
        this.resourceURL = new URL("http://localhost:" + this.port + "/hello");
    }

    @Test
    @DisplayName("POST /hello for valid JSON")
    public void testForCreatingGreeting() throws JSONException {
        //given
        Greeting greeting = new Greeting("id", "testing");
        String jsonRequest = getGreetingBody(greeting);

        //when
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(jsonRequest, headers);
        ResponseEntity<Greeting> response = restTemplate.postForEntity(resourceURL.toString(), entity, Greeting.class);

        //then
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getHeaders().getContentType().toString(), equalTo("application/json"));
        assertThat(response.getBody().getMessage(), equalTo("testing"));
        assertThat(response.getBody().getId(), equalTo("id"));
    }

    @Test
    @DisplayName("GET /hello for default method")
    public void testForGetDefaultMethod() throws JSONException {
        //given
        Greeting greeting = new Greeting("id", "testing");
        String jsonRequest = getGreetingBody(greeting);

        //when
        ResponseEntity<Greeting> response = restTemplate.getForEntity(resourceURL.toString(), Greeting.class);

        //then
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getHeaders().getContentType().toString(), equalTo("application/json"));
        assertThat(response.getBody().getMessage(), equalTo("Hello World!"));
        assertThat(response.getBody().getId(), equalTo("default"));
    }


    @Test
    @DisplayName("GET /hello for get greeting by ID")
    public void testForGetGreetingByID() throws JSONException {
        //given
        Greeting greeting = new Greeting("id", "testing");

        //when
        ResponseEntity<Greeting> response = restTemplate.getForEntity(resourceURL.toString() + "/" + greeting.getId(), Greeting.class);

        //then
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }


    private String getGreetingBody(Greeting greeting) throws JSONException {
        JSONObject json = new JSONObject().put("message", greeting.getMessage());

        if (greeting.getId() != null) {
            json.put("id", greeting.getId());
        }
        return json.toString();
    }

}
