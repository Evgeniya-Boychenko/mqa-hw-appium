package ru.netology.qa;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

import static org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class CalculatorUiAutomatorTest {

    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public void createDriver() throws MalformedURLException {
        var options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("any_name");
        options.setAppPackage("ru.netology.testing.uiautomator");
        options.setAppActivity("ru.netology.testing.uiautomator.MainActivity");

        driver = new AndroidDriver(URI.create("http://127.0.0.1:4723").toURL(), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeEach
    public void setUp() {
        String appPackage = "ru.netology.testing.uiautomator";
        driver.terminateApp(appPackage);
        driver.activateApp(appPackage);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                io.appium.java_client.AppiumBy.id("ru.netology.testing.uiautomator:id/userInput")
        ));
    }

    @Test
    public void testEmptyString() {
        MainPage mainPage = new MainPage(driver);

        String initialText = mainPage.getResultText();

        mainPage.enterText("   ");
        mainPage.clickChangeButton();

        Assertions.assertEquals(initialText, mainPage.getResultText());
    }

    @Test
    public void testNewActivity() {
        String testText = "Netology";

        MainPage mainPage = new MainPage(driver);
        mainPage.enterText(testText);
        mainPage.clickActivityButton();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                io.appium.java_client.AppiumBy.id("ru.netology.testing.uiautomator:id/text")
        ));

        ResultPage resultPage = new ResultPage(driver);
        Assertions.assertEquals(testText, resultPage.getText());
    }

    @AfterAll
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}