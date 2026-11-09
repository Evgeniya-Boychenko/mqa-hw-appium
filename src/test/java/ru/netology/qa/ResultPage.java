package ru.netology.qa;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class ResultPage {

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/text")
    private WebElement resultText;

    public ResultPage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public String getText() {
        return resultText.getText();
    }
}