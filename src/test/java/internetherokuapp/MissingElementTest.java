package internetherokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class MissingElementTest extends BaseTest{
    @Test(priority = 0)
    public void testElementDisappearsOnRefresh() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/disappearing_elements");

        boolean elementDisappeared = false;

        // Coba beberapa kali refresh
        for (int i = 0; i < 5; i++) {
            // Ambil jumlah elemen sebelum refresh
            Thread.sleep(1000);
            List<WebElement> elementsBeforeRefresh = driver.findElements(By.cssSelector("ul > li > a"));
            int numberOfElementsBeforeRefresh = elementsBeforeRefresh.size();

            // Refresh halaman
            driver.navigate().refresh();

            // Ambil jumlah elemen setelah refresh
            Thread.sleep(1000);
            List<WebElement> elementsAfterRefresh = driver.findElements(By.cssSelector("ul > li > a"));
            int numberOfElementsAfterRefresh = elementsAfterRefresh.size();

            // Cek jika ada elemen yang hilang
            if (numberOfElementsAfterRefresh < numberOfElementsBeforeRefresh) {
                elementDisappeared = true;
                break;
            }
        }

        // Asersi bahwa setidaknya satu elemen hilang
        Assert.assertTrue(elementDisappeared, "No element disappeared after multiple refresh attempts.");
    }
    @Test(priority = 1)
    public void testAllElementsVisibleOnLoad() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/disappearing_elements");

        // Ambil daftar elemen
        Thread.sleep(1000);
        List<WebElement> elements = driver.findElements(By.cssSelector("ul > li > a"));

        // Asersi bahwa semua elemen terlihat
        Assert.assertTrue(elements.size() >= 4, "Less than 4 elements are visible on initial load.");
    }
}
