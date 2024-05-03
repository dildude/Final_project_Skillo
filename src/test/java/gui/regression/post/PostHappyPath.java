package gui.regression.post;

import gui.base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class PostHappyPath extends BaseTest {
    @DataProvider(name = "PostTestDataProvider")
    public Object[][] getUsers() {
        File postPicture = new File("src\\test\\resources\\upload\\testUpload.jpg");
        String caption = "Testing create post caption";

        return new Object[][]{{
                "testingDemos", "testing",
                "testingDemos", postPicture, caption},
        };
    }

    @Test(dataProvider = "PostTestDataProvider")
    public void verifyUserCanCreateNewPost(String user,String password,String username,File file,String caption) {
        //TODO да се направи тест
    }
}
