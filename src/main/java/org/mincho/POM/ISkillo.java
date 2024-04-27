package org.mincho.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ISkillo {

    final String BASE_URL = "http://training.skillo-bg.com:4200";
    WebDriver driver;
    WebDriverWait wait;
    Logger log;


    public ISkillo(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        element.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").equals("complete");
    }

    public void typeTextInField(WebElement element, String inputText){
        wait.until(ExpectedConditions.visibilityOf(element));

        element.clear();
        element.sendKeys(inputText);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").equals("complete");
    }



}
