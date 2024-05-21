package org.mincho.POM;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends ISkillo{

    public static final String REG_PAGE_SUFIX = "users/register";

    @FindBy (css = ".text-center.mb-4")
    public WebElement  registrationPageHeaderTitle;
    @FindBy (name = "username")
    public WebElement regUsernameField;
    @FindBy (css = "input[placeholder='email']")
    public WebElement regEmailField;
    @FindBy (id = "defaultRegisterFormPassword")
    public WebElement regPasswordInputField;
    @FindBy (id = "defaultRegisterPhonePassword")
    public WebElement regConfirmPassField;
    @FindBy (id = "sign-in-button")
    public WebElement completeRegistration;
    @FindBy(xpath = "//div[@class=\"toast-message ng-star-inserted\"]") // ?? not sure if thats the xpath
    private WebElement popUpMsg;



    public RegistrationPage (WebDriver driver, Logger log){
        super(driver, log);

        PageFactory.initElements(driver, this);
    }
    public void openRegPage(){
        navigateTo(REG_PAGE_SUFIX);
    }

    public void provideNewName(String newUserName){
        typeTextInField(regUsernameField, newUserName);
    }

    public void provideEmailAddress (String emailAddress){
        typeTextInField(regEmailField, emailAddress);
    }

    public void providePassword(String password){
        typeTextInField(regPasswordInputField, password);
    }

    public void provideConfermationOfThePass(String confPass){
        typeTextInField(regConfirmPassField,confPass);
    }

    public void clickAndSubmit(){
        waitAndClick(completeRegistration);
    }

    public void provideAllForRegistration(String newUserName, String emailAddress, String password, String confPass ) {
        provideNewName(newUserName);
        provideEmailAddress(emailAddress);
        providePassword(password);
        provideConfermationOfThePass(confPass);
        clickAndSubmit();
    }

    public boolean isRegistrationTitleShown(){
        return isTitleShown(registrationPageHeaderTitle);
    }

    public boolean isPopUpMsgShown(){
        boolean isPopUpMsgShown = false;
        log.info(" ACTION @ Checking if the Pop up message is shown");
        try {
            wait.until(ExpectedConditions.visibilityOf(popUpMsg));
            log.info("CONFIRM # The pop up message is shown");
            isPopUpMsgShown = true;
        }catch (NoSuchElementException e){
            log.error("ERROR ! The pop up message is not presented");
            isPopUpMsgShown = false;
        }
        return isPopUpMsgShown;
    }
//TODO to make a method that checks the msg's
    /*
    aria-label="Email taken"
    aria-label="Registration failed!"
    aria-label="Username taken"
    */



}
