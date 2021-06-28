package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class BrowserHooks {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrowserHooks.class);

    @Value("${service.url}")
    public String serviceUrl;

    @Value("${selenide.downloadsfolder}")
    public String selenideDownloadsFolder;

    @Value("${selenide.reportsfolder}")
    public String selenideReportsFolder;

    @Value("${selenide.browser}")
    public String selenideBrowser;

    @Before("@frontend")
    public void setupBrowser() {
        LOGGER.info("Setting up selenide configuration...");
        browser = selenideBrowser;
        baseUrl = serviceUrl;
        startMaximized = true;
        headless = true;
        timeout = 10_000;
        downloadsFolder = selenideDownloadsFolder;
        reportsFolder = selenideReportsFolder;
        clickViaJs = false;
    }

    @After("@frontend")
    public void clearBrowserCookiesAndLocalStorage() {
        if (hasWebDriverStarted()) {
            clearBrowserCookies();
            clearBrowserLocalStorage();
            LOGGER.info("Browser cookies and local storage cleared.");
        }
    }
}
