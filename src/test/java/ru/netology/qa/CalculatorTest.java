package ru.netology.qa;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.Activity;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.PLATFORM_NAME;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class CalculatorTest {

    private AndroidDriver driver;

    @BeforeAll
    public void createDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(PLATFORM_NAME, "Android");
        caps.setCapability(DEVICE_NAME, "any_name");
        caps.setCapability(APP_PACKAGE, "ru.netology.testing.uiautomator");
        caps.setCapability(APP_ACTIVITY, "ru.netology.testing.uiautomator.MainActivity");
        caps.setCapability("automationName", "UiAutomator2");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
    }

    @BeforeEach
    public void setUp() {

        driver.closeApp();


        driver.startActivity(new Activity(
                "ru.netology.testing.uiautomator",
                "ru.netology.testing.uiautomator.MainActivity"
        ));


        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }

    @Test
    public void testEmptyString() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("ru.netology.testing.uiautomator:id/textToBeChanged")));

        String initialText = driver.findElement(By.id("ru.netology.testing.uiautomator:id/textToBeChanged")).getText();
        driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput")).clear();
        driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput")).sendKeys("   ");
        driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonChange")).click();

        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        String resultText = driver.findElement(By.id("ru.netology.testing.uiautomator:id/textToBeChanged")).getText();
        Assertions.assertEquals(initialText, resultText);
    }

    @Test
    public void testNewActivity() {
        String testText = "Netology";
        driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput")).sendKeys(testText);
        driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonActivity")).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ru.netology.testing.uiautomator:id/text")));
        String resultText = driver.findElement(By.id("ru.netology.testing.uiautomator:id/text")).getText();
        Assertions.assertEquals(testText, resultText);
    }

    @AfterAll
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}