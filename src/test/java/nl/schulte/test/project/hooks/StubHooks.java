package nl.schulte.test.project.hooks;

import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import io.cucumber.java.After;
import nl.schulte.test.project.service.MockService;
import nl.schulte.test.project.state.StubState;

import java.util.List;

public class StubHooks {

    private final MockService mockService;
    private final StubState stubState;

    public StubHooks(MockService mockService, StubState stubState) {
        this.mockService = mockService;
        this.stubState = stubState;
    }

    @After("@stubs")
    public void deleteStubs() {
        final List<StubMapping> stubs = stubState.getStubs();

        stubs.forEach(mockService::deleteStub);
        stubState.clearStubs();
    }
}
