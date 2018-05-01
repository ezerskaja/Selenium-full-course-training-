import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyTaskEight {
    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/");
    }

    @Test
    public void myTestEight() {
        List nonMatchProducts = new ArrayList();

        List<WebElement> products = driver.findElements(By.cssSelector("li.product"));

        for (WebElement product : products) {
            List<WebElement> productsStickers = product.findElements(By.cssSelector("div.sticker"));
            if (productsStickers.size() != 1) {
                nonMatchProducts.add(product.findElement(By.cssSelector("div.name")).getText());
            }
        }

        Assert.assertTrue("Products without sticker: " + nonMatchProducts.toString(), nonMatchProducts.size() == 0);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}