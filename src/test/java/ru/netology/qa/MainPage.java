package ru.netology.qa;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class MainPage {

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/textToBeChanged")
    private WebElement resultText;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/userInput")
    private WebElement inputField;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonChange")
    private WebElement changeButton;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonActivity")
    private WebElement activityButton;

    public MainPage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public String getResultText() {
        return resultText.getText();
    }

    public void enterText(String text) {
        inputField.clear();
        inputField.sendKeys(text);
    }

    public void clickChangeButton() {
        changeButton.click();
    }

    public void clickActivityButton() {
        activityButton.click();
    }
}