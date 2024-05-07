package org.mincho.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


public class LoginPage extends ISkillo {
    public static final String LOGIN_PAGE_SUFIX = "users/login";

    @FindBy(css = "p.h4")
    private WebElement loginPageHeaderTitle;
    @FindBy(name = "usernameOrEmail")
    private WebElement usernameInputField;
    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordInputField;
    @FindBy(xpath = "//span[contains(text(),'Remember me')]")
    private WebElement rememberMeLabelText;
    @FindBy(xpath = "//input[contains(@formcontrolname,'rememberMe')]")
    private WebElement rememberMeCheckBox;
    @FindBy(id = "sign-in-button")
    private WebElement loginFormSubmitButton;
    @FindBy(xpath = "//a[contains(.,'Register')]")
    private WebElement loginFormRegistrationLink;
    @FindBy(xpath = "//div[@class=\"toast-message ng-star-inserted\"]")
    private WebElement popUpMsg;

    //Create a constructor
    public LoginPage(WebDriver driver, Logger log) {
        super(driver,log);
        PageFactory.initElements(driver, this);
    }

    public void  provideUserName(String userName) {
        typeTextInField(usernameInputField,userName);
    }

    public void providePassword(String userPassword){
        typeTextInField(passwordInputField,userPassword);
    }

    public void clickSubmitButton(){
        waitAndClick(loginFormSubmitButton);
    }

    public void loginWithUserAndPassword(String userName, String password) {
        provideUserName(userName);
        providePassword(password);
        clickSubmitButton();
    }

    public  String getUserNamePlaceHolder () {
        wait.until(ExpectedConditions.visibilityOf(usernameInputField));
        return usernameInputField.getAttribute("value");
    }

    //TODO да видя трябва ли ми този метод
    public boolean isUserNamePlaceHolderCorrect(String expectedUserNamePlaceHolder) {
        boolean isPerRequirements = false;
        try {
            String actualUserNamePlaceHolder = getUserNamePlaceHolder();
            isPerRequirements = expectedUserNamePlaceHolder.equals(actualUserNamePlaceHolder);

        }catch (NoSuchElementException e){
            log.error("ERROR ! The username placeHolder is not correct");
            isPerRequirements = false;
        }
        return isPerRequirements;
    }

    // TODO Да влезнат 2та метода в теста
    public void msgStatusAfterSubmitSuccessfulLogin() {
        String expectedMsgText = "Successful login!";
        String msgText = popUpMsg.getText();
        Assert.assertEquals(msgText,expectedMsgText);
    }
    public void msgStatusAfterInvalidLogin() {
        String expectedMsgText ="Wrong username or password!";
        String msgText = popUpMsg.getText();
        Assert.assertEquals(msgText,expectedMsgText);
    }
    //TODO да направя 2ри тест с включен този метод
    public void selectingRememberMeCheckBox(){
        rememberMeCheckBox.click();
        System.out.println("Remember me is selected");
    }



}