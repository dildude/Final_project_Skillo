package gui.regression.post;

import gui.base.BaseTest;
import org.mincho.POM.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class PostHappyPath extends BaseTest {
    @DataProvider(name = "PostTestDataProvider")
    public Object[][] getUsers() {
        File postPicture = new File("src\\test\\resources\\upload\\testImg.jpg");
        String caption = "Testing create post caption";

        return new Object[][]{{
                "Minchotest", "123456",
                "Minchotest", postPicture, caption},
        };
    }

    @Test(dataProvider = "PostTestDataProvider")
    public void verifyUserCanCreateNewPost(String user, String password, String username, File file, String caption) {
        final String HOME_PAGE_URL = "posts/all";
        final String LOGIN_PAGE_URL = "users/login";

        HomePage homePage = new HomePage(driver, log);

        log.info("\n STEP 1: Open Skillo site");
        homePage.openHomePage();
        homePage.isUrlLoaded(HOME_PAGE_URL);
        log.info("\n STEP 2: Click on Login button");
        homePage.clickOnLoginButton();
        log.info("\n STEP 3: Is URL loaded");
        homePage.isUrlLoaded(LOGIN_PAGE_URL);
        log.info("\n STEP 4: Log in with correct credentials");

        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.loginWithUserAndPassword(user, password);
        log.info("\n STEP 5: Waiting for New Post button to be visible");
        homePage.isNewPostButtonToShown();
        log.info("\n STEP 6: Click on New Post button");
        homePage.clickOnNewPostButton();
        log.info("\n STEP 7: Upload new picture");

        PostPage postPage = new PostPage(super.driver, log);
        postPage.uploadPicture(file);
        log.info("\n STEP 8: Checking if the image is visible after upload");
        Assert.assertTrue(postPage.isImageVisible(), "The image is visible!");
        log.info("\n STEP 9: Checking if the image name is correct");
        Assert.assertEquals(file.getName(), postPage.getImageName(), "The image name is correct");
        log.info("\n STEP 10: Provide caption for the new image");
        postPage.providePostCaption(caption);
        log.info("\n STEP 11: Click on Create Button to create the new post");
        postPage.clickCreatePostButton();
        log.info("\n STEP 12: Checking if the post count is correct");

        ProfilePage profilePage = new ProfilePage(driver, log);
        Assert.assertEquals(profilePage.getPostCount(), 1, "The number of Posts is incorrect!");
        log.info("\n Step 13: Clicking on the first post");
        profilePage.clickPost(0);

        PostModal postModal = new PostModal(driver, log);
        log.info("\n STEP 14: Verifying that the image and Username are visible");
        Assert.assertTrue(postModal.isImageVisible(), "The image is visible!");
        Assert.assertEquals(postModal.getPostUser(), username);

        log.info("\n STEP 15: Deleting the new post so this test can be repeated");
        postModal.clickOnBinIcon();
        postModal.confirmDeletingPost();
    }
}
