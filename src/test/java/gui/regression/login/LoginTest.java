package gui.regression.login;

import gui.base.BaseTest;

import org.mincho.POM.*;

import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {

    @Test
    public void navigatingAndLoggingIn() {

        final String USERNAME = "Minchotest";
        final String PASSWORD = "123456";

        HomePage homePage = new HomePage(super.driver, log);

        log.info("\n STEP 1: Not logged in user has open the ISkilo HomePage.");
        homePage.openHomePage();
        boolean isLogOutButtonShown = homePage.isLogOutButtonShown();
        Assert.assertFalse(isLogOutButtonShown);

        log.info("\n STEP 2: The user has navigated to ISkilo LoginPage");
        homePage.clickOnLoginButton();

        log.info("\n STEP 3: The user has verified that the LoginPage is open as per requirements ");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.isLoginTitleShown();
        log.info("\n STEP 5: The user has provided a valid username");
        loginPage.provideUserName(USERNAME);

        log.info("\n STEP 6: The user has provided a valid password");
        loginPage.providePassword(PASSWORD);

        log.info("\n STEP 7: The user has clicked on login submit button");
        loginPage.clickSubmitButton();

    }

}
