package nl.schulte.test.project.config;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SelenideConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SelenideConfig.class);

    @Value("${selenide.browser}")
    public String selenideBrowser;

    @Value("${service.url}")
    public String serviceUrl;

    @Value("${selenide.headless}")
    public boolean selenideHeadless;

    @Value("${selenide.downloadsfolder}")
    public String selenideDownloadsFolder;

    @Value("${selenide.reportsfolder}")
    public String selenideReportsFolder;

    @PostConstruct
    public void setupBrowser() {
        LOGGER.info("Setting up selenide configuration...");
        com.codeborne.selenide.Configuration.browser = selenideBrowser;
        com.codeborne.selenide.Configuration.baseUrl = serviceUrl;
        com.codeborne.selenide.Configuration.headless = selenideHeadless;
        com.codeborne.selenide.Configuration.timeout = 10_000;
        com.codeborne.selenide.Configuration.downloadsFolder = selenideDownloadsFolder;
        com.codeborne.selenide.Configuration.reportsFolder = selenideReportsFolder;
        com.codeborne.selenide.Configuration.clickViaJs = false;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }
}
