package gui.e2e;

import gui.base.BaseTest;
import org.mincho.POM.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class EndToEnd extends BaseTest {

    File postPicture = new File("src\\test\\resources\\upload\\testImg.jpg");
    String caption = "Testing create post caption";


    @Test
    public void endToEndTest() {

        final String NEWUSERNAME = "TestMincho10";
        final String EMAIL = "crikigo9@yopmail.com";
        final String REGPASSWORD = "123456";
        final String CONFIRMPASSWORD = "123456";


        HomePage homePage = new HomePage(super.driver, log);
        log.info("\n STEP 1: Open iSkilo site.");
        homePage.openHomePage();
        log.info("\n Result: The website is open.");
        boolean isLogOutButtonShown = homePage.isLogOutButtonShown();
        Assert.assertFalse(isLogOutButtonShown);

        RegistrationPage registrationPage = new RegistrationPage(super.driver, log);
        log.info("\n STEP 2: Navigate to Registration page.");
        registrationPage.openRegPage();
        registrationPage.isRegistrationTitleShown();
        log.info("\n RESULT: The Page is open and title is visible.");
        log.info("\n STEP 3: Checking the place holders of the fields");
        registrationPage.isPlaceholderCorrect(registrationPage.regUsernameField, "Username");
        registrationPage.isPlaceholderCorrect(registrationPage.regEmailField,"email");
        registrationPage.isPlaceholderCorrect(registrationPage.regPasswordInputField,"Password");
        registrationPage.isPlaceholderCorrect(registrationPage.regConfirmPassField,"Confirm Password");
        log.info("\n STEP 4: Making a registration.");
        registrationPage.provideAllForRegistration(NEWUSERNAME, EMAIL, REGPASSWORD, CONFIRMPASSWORD);
        log.info("\n RESULT: The registration is successful.");
        log.info("\n STEP 5: Checking that the user is log in after registration.");
        homePage.isUrlLoaded("http://training.skillo-bg.com:4200/posts/all");

        log.info("\n STEP:6 Login out");
        homePage.clickOnLogOutButton();
        log.info("\n RESULT: The user is log out");

        LoginPage loginPage = new LoginPage(super.driver, log);
        log.info("\n STEP 7: Verify that the user is on login page.");
        loginPage.isLoginTitleShown();
        log.info("\n STEP 8: Checking the placeholders of the login page.");
        loginPage.isPlaceholderCorrect(loginPage.usernameInputField ,"Username or email");
        loginPage.isPlaceholderCorrect(loginPage.passwordInputField ,"Password");
        log.info("\n RESULT: The placeholders are correct.");
        log.info("\n STEP 9: Marking the \"remember me\" check box.");
        loginPage.selectingRememberMeCheckBox();
        log.info("\n RESULT: The check box is checked.");
        log.info("\n STEP 10: Entering credentials of the newly registered user and submitting.");
        loginPage.loginWithUserAndPassword(NEWUSERNAME, REGPASSWORD);
        log.info("\n RESULT: The correct credentials are entered and submit.");
        log.info("\n STEP 11: Verifying the submit message.");
        loginPage.msgStatusAfterSubmitSuccessfulLogin();
        log.info("\n RESULT: The message is shown");
        log.info("\n STEP 12: Verifying that the user is log in.");
        homePage.isNewPostButtonToShown();
        log.info("\n RESUL: The user is log in the \"New Post\" button is shown.");
        log.info("\n STEP 13: Navigating to \"New post\".");
        homePage.clickOnNewPostButton();
        log.info("\n RESULT: The user is on \"New post\" page.");

        PostPage postPage = new PostPage(super.driver, log);
        log.info("\n STEP 14: Upload new post picture.");
        postPage.uploadPicture(postPicture);
        Assert.assertTrue(postPage.isImageVisible(), "The image is visible!");
        Assert.assertEquals(postPicture.getName(), postPage.getImageName(), "The image name is correct.");
        log.info("\n STEP 15: Enter caption.");
        postPage.providePostCaption(caption);
        log.info("\n STEP 16: Create the new post.");
        postPage.clickCreatePostButton();
        log.info("\n STEP 17: Verifying the post count.");
        ProfilePage profilePage = new ProfilePage(driver, log);
        Assert.assertEquals(profilePage.getPostCount(), 1, "The number of Posts is correct!");
        log.info("\n STEP 18: Open the new post.");
        profilePage.clickPost(0);
        log.info("\n RESULT: The new post is open.");

        PostModal postModal = new PostModal(driver, log);
        log.info("\n STEP 19: Verifying that the image is visible and the username is correct.");
        Assert.assertTrue(postModal.isImageVisible(), "The image is visible!");
        Assert.assertEquals(postModal.getPostUser(), NEWUSERNAME);
        log.info("\n STEP 20: Deleting the new post");
        postModal.clickOnBinIcon();
        postModal.confirmDeletingPost();
        log.info("\n RESULT: The post is deleted");

    }
}
