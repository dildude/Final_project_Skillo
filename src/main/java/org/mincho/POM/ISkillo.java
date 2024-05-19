package org.mincho.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
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
        log.info("The user has clicked on element{}", element);

        waitPageTobeFullLoaded();
    }

    public void typeTextInField(WebElement element, String inputText) {
        wait.until(ExpectedConditions.visibilityOf(element));

        element.clear();
        element.sendKeys(inputText);

        waitPageTobeFullLoaded();
    }

    public void navigateTo(String pageURLsufix) {
        String currentURL = BASE_URL + pageURLsufix;

        driver.get(currentURL);
        log.info("CONFIRM # The user has navigating to : {}", currentURL);

        waitPageTobeFullLoaded();
    }

    public boolean isUrlLoaded(String pageURL) {
        waitPageTobeFullLoaded();
        log.info("CONFIRM # The page URL is loaded");

        return wait.until(ExpectedConditions.urlContains(pageURL));
    }


    public void waitPageTobeFullLoaded() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").equals("complete");
        log.info("DOM tree is fully loaded");
    }

    public String getPlaceholder(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));

        return element.getAttribute("placeholder");
    }

    public boolean isPlaceholderCorrect(WebElement element, String expectedPlaceholder) {
        try {
            String actualPlaceholder = getPlaceholder(element);

            return expectedPlaceholder.equals(actualPlaceholder);
        } catch (NoSuchElementException e) {
            log.error("ERROR! The placeholder for the element is not correct or element is not found.", e);

            return false;
        }
    }

    public boolean isTitleShown(WebElement element) {
        boolean isTitleShown = false;
        log.info(" ACTION @ The user is verifying if the Registration title is shown");
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            log.info("CONFIRM # The Registration title is shown to the user");
            isTitleShown = true;
        } catch (NoSuchElementException e) {
            log.error("ERROR ! The title is not presented the user is not on Registration page");
        }
        return isTitleShown;
    }

}
