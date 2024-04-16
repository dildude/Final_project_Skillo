package org.Mincho.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    //1. Static vars
    public static final String HOME_PAGE_URL = "http://training.skillo-bg.com:4300/posts/all";
    private WebDriver driver;
    private WebDriverWait wait;

    //2. WebElements (UI mappings)
    @FindBy (id = "nav-link-login")
    private WebElement navigationLoginButton;

    //3. Constructor
    public HomePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver,Duration.ofSeconds(10));
        PageFactory.initElements(driver,this); //This need to be done every time so it can initialise the @FindBy elements

    }
    //4. User Actions
    public void openHomePage(){
        this.driver.get(HOME_PAGE_URL);
        wait.until(ExpectedConditions.urlContains(HOME_PAGE_URL));
    }
    public void navigateToLoginPageViaClickOnNavigationLoginButton(){
        waitAndClick(navigationLoginButton);
    }

    //6. Assertions


    //6. Support verification methods
    private void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
        System.out.println("THE USER HAS CLICKED ON "+ element);
    }



}
