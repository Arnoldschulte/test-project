package nl.schulte.test.project.hooks;

import io.cucumber.java.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class BrowserHooks {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrowserHooks.class);

    @After("@frontend")
    public void clearBrowserCookiesAndLocalStorage() {
        if (hasWebDriverStarted()) {
            clearBrowserCookies();
            clearBrowserLocalStorage();
            LOGGER.info("Browser cookies and local storage cleared.");
        }
    }
}
