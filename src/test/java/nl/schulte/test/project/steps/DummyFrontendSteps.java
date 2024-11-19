package nl.schulte.test.project.steps;

import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.Gegeven;
import nl.schulte.test.project.domain.Number;
import nl.schulte.test.project.service.XmlParseService;
import nl.schulte.test.project.state.State;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class DummyFrontendSteps {

    private static final String OBSTACLE_COURSE_URL = "https://obstaclecourse.tricentis.com/Obstacles/";

    private final State state;
    private final XmlParseService xmlParseService;

    public DummyFrontendSteps(XmlParseService xmlParseService, State state) {
        this.state = state;
        this.xmlParseService = xmlParseService;
    }

    @Gegeven("ik ben op de Tricentis obstacle pagina")
    public void ikBenOpDeTricentisObstaclePagina() {
        baseUrl = OBSTACLE_COURSE_URL;
        open();
    }

    @Als("ik naar obstacle {int} ga")
    public void ikNaarObstacleGa(int obstacleNumber) {
        open(String.valueOf(obstacleNumber));
    }

    @Als("ik op de juiste knop klik")
    public void ikOpDeJuisteKnopKlik() {
        // Bij voorkeur een pageObject gebruiken; zie ook andere elementen
        $("#thisoneistheright")
                .shouldBe(visible)
                .shouldHave(text("I AM THE ONE!"))
                .shouldBe(enabled)
                .click();
    }

    @Als("ik op de verkeerde knop klik")
    public void ikOpDeVerkeerdeKnopKlik() {
        // Bij voorkeur een pageObject gebruiken; zie ook andere elementen
        elements("#id")
                .first()
                .shouldBe(visible)
                .shouldHave(text("I AM THE ONE!"))
                .click();
    }

    @Dan("zie ik een succesmelding")
    public void zieIkEenSuccesmelding() {
        $(".sweet-alert")
                .shouldBe(visible)
                .shouldHave(text("You solved this automation problem."));
    }

    @Dan("zie ik geen succesmelding")
    public void zieIkgeenSuccesmelding() {
        $(".sweet-alert").shouldNotBe(visible);
    }

    @Dan("download ik het XML bestand")
    public void downloadIkHetXMLBestand() throws IOException, ParserConfigurationException, SAXException {
        final File file = $("#downloadSolution").download();
        final List<Number> numbers = xmlParseService.parseXmlNumbers(file);
        state.setNumbers(numbers);
    }

    @Als("ik het volledige nummer van {string} invul")
    public void ikHetVolledigeNummerVanXInvul(String naam) {
        final Number numberByName = getNumberByName(naam);
        final String fullNumber = numberByName.getPrefix() + numberByName.getNumber();

        $("#NumberSue").val(fullNumber);
    }

    private Number getNumberByName(String naam) {
        return state.getNumbers().stream()
                .filter(number -> number.getId().equalsIgnoreCase(naam))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Geen number gevonden van: " + naam));
    }

    @Als("ik de zoekterm invul")
    public void ikDeZoektermInvul() {
        final String zoekterm = $("#typeThis").shouldBe(visible).getText();
        $("input.select2-search__field").val(zoekterm);
    }

    @Dan("tel ik het aantal resultaten en verstuur deze")
    public void telIkHetAantalResultatenEnVerstuurDeze() {
        $("#select2-autocomplete-results").shouldBe(visible);
        final int aantalResultaten = $$("li.select2-results__option").size();

        $("input#entryCount").val(String.valueOf(aantalResultaten));
    }
}
