package nl.schulte.test.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.admin.model.SingleStubMappingResult;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.HttpHeaders;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Service
public class MockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockService.class);

    private final WireMock wireMock;
    private final ObjectMapper objectMapper;

    public MockService(WireMock wireMock, ObjectMapper objectMapper) {
        this.wireMock = wireMock;
        this.objectMapper = objectMapper;
    }

    public StubMapping stub(String url, String httpMethod, Object requestBody, Object responseBody, HttpStatus httpStatus, HttpHeaders httpHeaders) {
//      TODO: Doe nog niks met requestBody
        final StubMapping stubMapping = wireMock.register(stub(url, httpMethod, responseBody, httpStatus.value(), httpHeaders));
        LOGGER.info("Volgende stub gemaakt: \n[{}]", stubMapping);

        return stubMapping;
    }

    private MappingBuilder stub(String url, String httpMethod, Object responseBody, int status, HttpHeaders httpHeaders) {
        return getMappingBuilder(httpMethod, url)
                .atPriority(1)
                .willReturn(aResponse()
                        .withStatus(status)
                        .withBody(getObjectAsString(responseBody))
                        .withHeaders(httpHeaders)
                );
    }

    private MappingBuilder getMappingBuilder(String httpMethod, String url) {
        return switch (httpMethod.toUpperCase()) {
            case "GET" -> get(url);
            case "POST" -> post(url);
            case "PUT" -> put(url);
            case "DELETE" -> delete(url);
            default -> throw new NotImplementedException("Following HTTP method for stub not implemented: " + httpMethod);
        };
    }

    private String getObjectAsString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Fout in objectmapper: " + e.getMessage());
        }
    }

    public void deleteStub(StubMapping stubMapping) {
        final SingleStubMappingResult mapping = wireMock.getStubMapping(stubMapping.getId());
        if (mapping != null) {
            wireMock.removeStubMapping(stubMapping);

            LOGGER.info("Stub verwijderd: \n[{}]", stubMapping);
        } else {
            LOGGER.error("Volgende stub kon niet worden verwijderd, deze is niet gestubt : \n[{}]", stubMapping);
        }
    }
}
