package gui.regression.login;

import gui.base.BaseTest;
import org.mincho.POM.HomePage;
import org.mincho.POM.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

    @Test
    public void navigationAndRegistration(){

        final String NEWUSERNAME = "TestMincho3";
        final String EMAIL = "crikigo3@yopmail.com";
        final String PASSWORD = "123456";
        final String CONFIRMPASSWORD = "123456";

        RegistrationPage registrationPage = new RegistrationPage(super.driver, log);

        log.info("STEP 1: Open Registration page directly");
        registrationPage.openRegPage();
        boolean isRegPageTitleShown = registrationPage.isRegistrationTitleShown();
        Assert.assertTrue(isRegPageTitleShown);

        log.info("STEP 2: Entering the required fields");
        registrationPage.provideNewName(NEWUSERNAME);
        registrationPage.provideEmailAddress(EMAIL);
        registrationPage.providePassword(PASSWORD);
        registrationPage.provideConfermationOfThePass(CONFIRMPASSWORD);

        log.info("STEP 3: Click nad submit the registration form");
        registrationPage.clickAndSubmit();

        log.info("STEP 4: Verifying the pop-up message is shown");
        boolean isPopUpMsgShown = registrationPage.isPopUpMsgShown();
        Assert.assertTrue(isPopUpMsgShown);

        log.info("STEP 5: The newly registered user is send to the home page");
        HomePage homePage = new HomePage(super.driver, log);
        boolean isLogOutButtonShown = homePage.isLogOutButtonShown();
        Assert.assertTrue(isLogOutButtonShown);
    }
}
