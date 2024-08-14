package internetherokuapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/Users/macbookpro/Downloads/chromedriver-mac-x64-2/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
