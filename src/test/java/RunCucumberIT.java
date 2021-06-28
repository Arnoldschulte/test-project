import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"steps", "config"},
        plugin = {"json:target/cucumber.json"},
        features = "classpath:/features",
        tags = "not @ignore"
)
public class RunCucumberIT {
}
