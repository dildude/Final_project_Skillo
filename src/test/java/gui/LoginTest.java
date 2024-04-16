package gui;

import gui.base.BaseTest;
import org.mincho.POM.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginWhirValidCreds() {
        LoginPage loginPage = new LoginPage(super.driver);

        loginPage.openLoginPage();
        loginPage.validationOfTheHeaderInLoginPage();
        System.out.println("Login page is open");
        loginPage.enterCredentialsValid();
        System.out.println("Valid credentials are entered");
        loginPage.selectingRememberMeCheckBox();
        loginPage.submitCredentials();
        System.out.println("The valid credentials are submit");
        loginPage.msgStatusAfterSubmitSuccessfulLogin();
        System.out.println("The user is login");
    }

    @Test
    public void loginWhitInvalidCreds(){

        LoginPage loginPage = new LoginPage(super.driver);

        loginPage.openLoginPage();
        loginPage.validationOfTheHeaderInLoginPage();
        System.out.println("Login page is open");
        loginPage.enterCredentialsInvalid();
        System.out.println("Invalid credentials are entered");
        loginPage.submitCredentials();
        System.out.println("The invalid credentials are submit");
        loginPage.msgStatusAfterInvalidLogin();

    }
}
