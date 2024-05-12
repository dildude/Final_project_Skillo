package gui.e2e;

import gui.base.BaseTest;
import org.mincho.POM.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class EndToEndTest extends BaseTest {
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
    public void verifyUserCanCreateNewPost(
            String user,
            String password,
            String username,
            File file,
            String caption) {
        final String HOME_PAGE_URL = "posts/all";
        final String LOGIN_PAGE_URL = "users/login";

        HomePage homePage = new HomePage(driver, log);


        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.navigateTo(LOGIN_PAGE_URL);
        loginPage.loginWithUserAndPassword(user, password);

        boolean isShownLogOutButton = homePage.isLogOutButtonShown();
        Assert.assertTrue(isShownLogOutButton);

        homePage.clickOnNewPostButton();
        PostPage postPage = new PostPage(super.driver, log);

        postPage.uploadPicture(file);

        Assert.assertTrue(postPage.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(file.getName(), postPage.getImageName(), "The image name is incorrect!");

        postPage.providePostCaption(caption);
        postPage.clickCreatePostButton();

        ProfilePage profilePage = new ProfilePage(driver, log);
        Assert.assertEquals(profilePage.getPostCount(), 1, "The number of Posts is incorrect!");
        profilePage.clickPost(0);

        PostModal postModal = new PostModal(driver, log);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postModal.getPostUser(), username);
    }
}
