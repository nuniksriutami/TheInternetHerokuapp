package internetherokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FileUploadTest extends BaseTest {

    @Test(priority = 0)
    public void testSuccessFileUpload() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/upload");

        Thread.sleep(1000);
        WebElement uploadInput = driver.findElement(By.id("file-upload"));
        uploadInput.sendKeys("/Users/macbookpro/Downloads/testfile.txt");

        Thread.sleep(1000);
        WebElement uploadButton = driver.findElement(By.id("file-submit"));
        uploadButton.click();

        Thread.sleep(1000);
        WebElement uploadedFileName = driver.findElement(By.id("uploaded-files"));
        Assert.assertTrue(uploadedFileName.getText().contains("file.txt"), "File upload failed or file name is incorrect!");
    }
    @Test(priority = 1)
    public void testUploadWithoutSelectingFile() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/upload");

        Thread.sleep(1000);
        WebElement uploadButton = driver.findElement(By.id("file-submit"));
        uploadButton.click();

        Thread.sleep(1000);
        WebElement errorMessage = driver.findElement(By.tagName("h1"));
        Assert.assertTrue(errorMessage.isDisplayed(),"Error message is not displayed for invalid file type!");
    }
    @Test(priority = 2)
    public void testUploadEmptyFile() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/upload");

        Thread.sleep(1000);
        WebElement uploadInput = driver.findElement(By.id("file-upload"));
        uploadInput.sendKeys("/Users/macbookpro/Downloads/empty.txt");

        Thread.sleep(1000);
        WebElement uploadButton = driver.findElement(By.id("file-submit"));
        uploadButton.click();

        Thread.sleep(1000);
        WebElement fileName = driver.findElement(By.id("uploaded-files"));
        String actualFileName = fileName.getText();
        String expectedFileName = "empty.txt"; // Sesuaikan dengan nama file yang sebenarnya

        // Assert file upload success and correct file name
        Assert.assertTrue(actualFileName.equals(expectedFileName),
                "Empty file upload failed or file name is incorrect! Expected: " + expectedFileName + " but was: " + actualFileName);
    }
}
