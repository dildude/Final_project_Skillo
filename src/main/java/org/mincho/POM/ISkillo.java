package org.mincho.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ISkillo {

    WebDriver driver;
    WebDriverWait wait;


    public ISkillo(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        element.click();
        System.out.println("Element is clicked: " + element);
    }

    public void typeTextInField(WebElement element, String text){
        wait.until(ExpectedConditions.visibilityOf(element));

        element.clear();
        element.sendKeys(text);

        System.out.println("The user has typed in " + element);
    }


}
