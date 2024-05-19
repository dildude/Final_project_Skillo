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
    private WebElement  registrationPageHeaderTitle;
    @FindBy (name = "username")
    private WebElement regUsernameField;
    @FindBy (css = "input[placeholder='email']")
    private WebElement regEmailField;
    @FindBy (id = "defaultRegisterFormPassword")
    private WebElement regPasswordInputField;
    @FindBy (id = "defaultRegisterPhonePassword")
    private WebElement regConfirmPassField;
    @FindBy (id = "sign-in-button")
    private WebElement completeRegistration;
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

    //TODO to move it to skillo and make it so it can be used for login page as well
    public boolean isRegistrationTitleShown(){
        boolean isTitleShown = false;
        log.info(" ACTION @ The user is verifying if the Registration title is shown");
        try {
            wait.until(ExpectedConditions.visibilityOf(registrationPageHeaderTitle));
            log.info("CONFIRM # The Registration title is shown to the user");
            isTitleShown = true;
        }catch (NoSuchElementException e){
            log.error("ERROR ! The title is not presented the user is not on Registration page");
            isTitleShown = false;
        }
        return  isTitleShown;
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
