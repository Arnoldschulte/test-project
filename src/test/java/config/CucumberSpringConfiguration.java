package config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
        TestConfig.class,
        RestConfig.class,
        StubConfig.class})
@CucumberContextConfiguration
public class CucumberSpringConfiguration {
}
