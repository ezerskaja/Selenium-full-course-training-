import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MyTaskForAttempts {
    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/en/");
    }

    @Test
    public void myTestEight() {
        WebElement login = driver.findElement(By.cssSelector("[name = login]"));
        System.out.println("Value is: " + login.getAttribute("value"));

        System.out.println("Text content is: " + login.getAttribute("textContent)"));
        System.out.println("Outer HTML value is: " + login.getAttribute("outerHTML)"));
        driver.findElement(By.cssSelector("#breadcrumbs a"));
      //  login.getAttribute("a[href^="https"]");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

