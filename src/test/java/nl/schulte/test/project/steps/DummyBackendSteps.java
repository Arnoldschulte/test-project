package nl.schulte.test.project.steps;

import com.github.tomakehurst.wiremock.http.HttpHeaders;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.Gegeven;
import nl.schulte.test.project.service.MockService;
import nl.schulte.test.project.service.RestService;
import nl.schulte.test.project.state.State;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class DummyBackendSteps {

    private final State state;
    private final MockService mockService;
    private final RestService restService;

    public DummyBackendSteps(State state, MockService mockService, RestService restService) {
        this.state = state;
        this.mockService = mockService;
        this.restService = restService;
    }

    @Gegeven("het getal {int}")
    public void hetGetal(int beginGetal) {
        state.setBegingetal(beginGetal);
    }

    @Als("ik daar {int} bij optel")
    public void ikDaarBijOptel(int getalOptellen) {
        final int nieuwGetal = state.getBegingetal() + getalOptellen;
        state.setBegingetal(nieuwGetal);
    }

    @Als("ik daar {int} bij aftrek")
    public void ikDaarBijAftrek(int getalAftrekken) {
        final int nieuwGetal = state.getBegingetal() - getalAftrekken;
        state.setBegingetal(nieuwGetal);
    }

    @Dan("heb ik een totaal van {int}")
    public void hebIkEenTotaalVan(int verwachtTotaalGetal) {
        assertThat(state.getBegingetal(), is(verwachtTotaalGetal));
    }

    @Gegeven("een dummy stub is aanwezig met de tekst {string}")
    public void eenDummyStubIsAanwezigMetDeTekstX(String tekst) {
        final StubMapping stub = mockService.stub("/dummy", HttpMethod.GET, null, tekst, HttpStatus.OK, HttpHeaders.noHeaders());
        state.getStubState().addStub(stub);
    }

    @Als("ik via REST {string} bevraag met een {string}")
    public void ikViaRestUrlBevraagMetEenMethod(String url, String httpMethod) {
        final ResponseEntity<String> response = restService.makeRestCall(url, HttpMethod.valueOf(httpMethod), null);
        state.getRestState().setRestResponse(response);
    }

    @Dan("krijg ik een status {int}")
    public void krijgIkEenStatusX(int statuscode) {
        final ResponseEntity<String> response = state.getRestState().getRestResponse();
        assertThat(response.getStatusCode().value(), is(statuscode));
    }

    @Dan("krijg ik de tekst {string} terug in de body")
    public void krijgIkDeTekstXTerugInDeBody(String verwachteTekst) {
        final ResponseEntity<String> response = state.getRestState().getRestResponse();
        assertThat(response.getBody(), containsString(verwachteTekst));
    }
}
