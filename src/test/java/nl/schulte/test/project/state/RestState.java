package nl.schulte.test.project.state;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RestState {

    private ResponseEntity<String> restResponse;

    public ResponseEntity<String> getRestResponse() {
        return restResponse;
    }

    public void setRestResponse(ResponseEntity<String> restResponse) {
        this.restResponse = restResponse;
    }
}
