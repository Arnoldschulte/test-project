package nl.schulte.test.project.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StubConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    @ConditionalOnProperty(value = "wiremock.server.standalone", havingValue = "false")
    public WireMockServer wireMockServer(
            @Value("${wiremock.server.port}") Integer port
    ) {
        return new WireMockServer(port);
    }

    @Bean(destroyMethod = "")
    public WireMock wireMock(
            @Value("${wiremock.server.host}") String host,
            @Value("${wiremock.server.port}") Integer port
    ) {
        return new WireMock(host, port);
    }
}
