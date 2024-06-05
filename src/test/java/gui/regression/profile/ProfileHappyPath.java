package gui.regression.profile;

import gui.base.BaseTest;

import org.mincho.POM.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class ProfileHappyPath extends BaseTest {
    @DataProvider(name = "PostTestDataProvider")
    public Object[][] getUser() {
        File postPicture = new File("src\\test\\resources\\upload\\testProfilePic.jpg");

        return new Object[][]{{
                "Minchotest", "123456", postPicture,},
        };
    }

    @Test(dataProvider = "PostTestDataProvider")
    public void verifyUserCanUpdateProfilePicture(String user, String password, File file) {
        final String PROFILE_PAGE_SUFIX = "users/5622";

        HomePage homePage = new HomePage(driver, log);
        log.info("\n STEP 1: Open site");
        homePage.openHomePage();
        log.info("\n STEP 2: Navigate to login page");
        homePage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver, log);
        log.info("\n STEP 3: Log in with correct creds");
        loginPage.loginWithUserAndPassword(user, password);
        ProfilePage profilePage = new ProfilePage(driver, log);
        log.info("\n STEP 4: Click on Profile button");
        profilePage.clickOnProfileButton();
        log.info("\n STEP 5: Verify Username");
        String userNameTitle = profilePage.getUsername();
        Assert.assertEquals(userNameTitle, user, "The Username is equal to the one in the title");
        log.info("\n STEP 6: Upload Profile Picture");
        profilePage.uploadProfilePic(file);
        log.info("\n STEP 7: Verifying that the image is uploaded");
        Assert.assertTrue(profilePage.isProfilePicDisplayed());
        log.info("END");


    }
}
