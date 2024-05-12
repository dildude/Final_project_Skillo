package org.mincho.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ISkillo {

    final String BASE_URL = "http://training.skillo-bg.com:4200/";
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
        log.info("The user has clicked on element" + element);

        waitPageTobeFullLoaded();

    }

    public void typeTextInField(WebElement element, String inputText){
        wait.until(ExpectedConditions.visibilityOf(element));

        element.clear();
        element.sendKeys(inputText);

        waitPageTobeFullLoaded();
    }

    public void navigateTo(String pageURLsufix){
        String currentURL = BASE_URL + pageURLsufix;

        driver.get(currentURL);
        log.info("CONFIRM # The user has navigating to : " +currentURL);

        waitPageTobeFullLoaded();
    }

    public boolean isUrlLoaded(String pageURL){
        waitPageTobeFullLoaded();
        log.info("CONFIRM # The page URL is loaded");
        return wait.until(ExpectedConditions.urlContains(pageURL));
    }


    public void waitPageTobeFullLoaded(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").equals("complete");
        log.info("DOM tree is fully loaded");
    }

}
