package nl.schulte.test.project.service;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RestService {

    private final TestRestTemplate testRestTemplate;

    public RestService(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    public ResponseEntity<String> makeRestCall(String url, HttpMethod httpMethod, String body) {
        return testRestTemplate.exchange(url, httpMethod, new HttpEntity<>(body), String.class);
    }
}
