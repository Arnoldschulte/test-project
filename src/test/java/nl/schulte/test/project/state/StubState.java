package nl.schulte.test.project.state;

import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

@Component
public class StubState {

    private static final Logger LOGGER = LoggerFactory.getLogger(StubState.class);

    private final List<StubMapping> stubs = new ArrayList<>();

    public void addStub(StubMapping stubMapping) {
        stubs.add(stubMapping);

        LOGGER.info("Stub toegevoegd aan state:\n[{}]", stubMapping);
    }

    public void clearStubs() {
        if (!stubs.isEmpty()) {
            stubs.clear();

            LOGGER.info("Stubs verwijderd uit state");
        }
    }

    public void deleteStub(StubMapping stubMapping) {
        if (stubs.contains(stubMapping)) {
            stubs.remove(stubMapping);

            LOGGER.info("Stub verwijderd uit state:\n[{}]", stubMapping);
        } else {
            LOGGER.error("De volgende stub niet gevonden in state:\n[{}]", stubMapping);
        }
    }

    public List<StubMapping> getStubs() {
        return unmodifiableList(stubs);
    }
}
