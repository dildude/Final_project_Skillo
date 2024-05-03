package gui;

import gui.base.BaseTest;
import org.mincho.POM.HomePage;
import org.mincho.POM.LoginPage;
import org.testng.annotations.Test;

public class TestClass extends BaseTest {




 @Test
    public void testing(){
     HomePage homePage = new HomePage(super.driver, log);
     homePage.openHomePage();
 }
}
