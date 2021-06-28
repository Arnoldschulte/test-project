package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestConfig {

    @Bean
    public TestRestTemplate testRestTemplate(
            @Value("${service.rest.url}") String url,
            @Value("${service.rest.port}") Integer port) {
        final TestRestTemplate testRestTemplate = new TestRestTemplate();
        final String uri = String.format("http://%s:%d", url, port);
        testRestTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(uri));
        return testRestTemplate;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .findAndRegisterModules()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
}
