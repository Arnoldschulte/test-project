package state;

import domain.Number;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component(SCOPE_CUCUMBER_GLUE)
public class State {

    private final StubState stubState;
    private final RestState restState;
    private int begingetal = 0;
    private List<Number> numbers = new ArrayList<>();

    public State(StubState stubState, RestState restState) {
        this.stubState = stubState;
        this.restState = restState;
    }

    public RestState getRestState() {
        return restState;
    }

    public StubState getStubState() {
        return stubState;
    }

    public int getBegingetal() {
        return begingetal;
    }

    public void setBegingetal(int begingetal) {
        this.begingetal = begingetal;
    }

    public List<Number> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public void setNumbers(List<Number> numbers) {
        this.numbers = numbers;
    }
}
