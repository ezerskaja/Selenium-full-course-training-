import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class mySecondTest {

    private WebDriver driver;
    @Before
    public void start (){
        driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void mySecondTest() {
        driver.get("http://localhost/litecart/admin/");
        WebElement u = driver.findElement(By.name("username"));
        u.sendKeys("admin");
        WebElement p = driver.findElement(By.name("password"));
        p.sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
