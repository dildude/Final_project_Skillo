package gui.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeTest
    public void initiatingDriver(){
        this.driver = new ChromeDriver();

        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        System.out.println("* * * * The driver is started * * * *");
    }

    @AfterTest
    public void quitingDriver(){
        if (this.driver != null){
            this.driver.quit();
            System.out.println("* * * * The driver is closed * * * *");
        }
    }

}
