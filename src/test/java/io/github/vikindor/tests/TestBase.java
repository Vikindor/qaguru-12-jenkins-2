package io.github.vikindor.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.vikindor.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.lang.reflect.Method;
import java.util.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void setupConfig() {
        Configuration.remote           = System.getProperty("remoteUrl");
        Configuration.browser          = System.getProperty("browser", "chrome");
        Configuration.browserVersion   = System.getProperty("browserVersion");
        Configuration.browserSize      = System.getProperty("browserSize", "1920x1080");
        Configuration.baseUrl          = System.getProperty("baseUrl", "https://demoqa.com");
        Configuration.pageLoadStrategy = "eager";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments(TestInfo testInfo) {
        String methodName = testInfo.getTestMethod().map(Method::getName).get();

        Attach.screenshotAs("Screenshot_" + methodName);
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

        closeWebDriver();
    }
}
