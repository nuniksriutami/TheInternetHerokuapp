package internetherokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test(priority = 0)
    public void testValidLogin() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/login");

        // Menggunakan driver yang sudah diinisialisasi
        Thread.sleep(1000);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");

        Thread.sleep(1000);
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");

        Thread.sleep(1000);
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        // Delay to wait for the redirection
        Thread.sleep(2000);

       // Verify that the URL is correct after login
//        String expectedUrl = "https://the-internet.herokuapp.com/secure";
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://the-internet.herokuapp.com/secure","Failed to login with valid credentials!");
    }
    @Test(priority = 1)
    public void testInvalidUsername() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/login");

        Thread.sleep(1000);
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("test");

        Thread.sleep(1000);
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");

        Thread.sleep(1000);
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        // Delay to wait for the error message
        Thread.sleep(2000);

        // Verify that the error message is displayed
        WebElement errorMessage = driver.findElement(By.id("flash"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed for invalid Username!");
    }
    @Test(priority = 2)
    public void testInvalidPassword() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/login");

        Thread.sleep(1000);
        WebElement username =driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");

        Thread.sleep(1000);
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("test!");

        Thread.sleep(1000);
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        Thread.sleep(1000);
        WebElement errorMessage = driver.findElement(By.id("flash"));
        Assert.assertTrue(errorMessage.isDisplayed(),"Error message not displayed for invalid password!");
    }
    @Test(priority = 3)
    public void blankUsernamePassword() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/login");

        Thread.sleep(1000);
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        Thread.sleep(1000);
        WebElement errorMessage = driver.findElement(By.id("flash"));
        Assert.assertTrue(errorMessage.isDisplayed(),"Error message not displayed for blank username and password!");
    }
    // masking password
    @Test(priority = 4)
    public void testPasswordMasking() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/login");
        // Find the password field
        Thread.sleep(1000);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!");

        // Verify the password field's type is 'password'
        String fieldType = passwordField.getAttribute("type");
        Assert.assertEquals(fieldType, "password", "Password field is not masked!");
    }
//    // Handling missing element
//    @Test(priority = 3)
//    public void testMissingElement() {
//        driver.get("https://the-internet.herokuapp.com/missing_element");
//
//        // Try to find the missing element
//        try {
//            WebElement missingElement = driver.findElement(By.id("non-existent-element"));
//            Assert.fail("Element should not exist, but it was found.");
//        } catch (NoSuchElementException e) {
//            // Pass the test if the element is not found, as expected
//            Assert.assertTrue(true, "Element is missing as expected.");
//        }
//    }
//    @AfterMethod
//    public void tearDown() {
//        // Menutup browser setelah tes selesai
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
