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

public class myTaskSeven {
    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.cssSelector("[name=username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=password]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=login]")).click();
    }

    @Test
    public void myTestSeven() {
            List missedItems = new ArrayList();

            List<WebElement> menuElements = driver.findElements(By.cssSelector("ul#box-apps-menu > li"));
            for (int i = 1; i <= menuElements.size(); i++) {
                WebElement menuElement = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(" + i + ")"));
                String menuItemTitle = menuElement.getText();
                menuElement.click();

                if (driver.findElements(By.cssSelector("h1")).size() == 0) missedItems.add(menuItemTitle);

                List<WebElement> subMenuElements = driver.findElements(By.cssSelector("ul#box-apps-menu > li:nth-child(" + i + ") li"));

                for (int j = 2; j <= subMenuElements.size(); j++) {
                    WebElement subMenuElement = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(" + i + ") li:nth-child(" + j + ")"));
                    String subMenuElementTitle = subMenuElement.getText();
                    subMenuElement.click();

                    if (driver.findElements(By.cssSelector("h1")).size() == 0) missedItems.add(subMenuElementTitle);

                }
            }

            Assert.assertTrue("Menu items without header: " + missedItems.toString(), missedItems.size() == 0);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
