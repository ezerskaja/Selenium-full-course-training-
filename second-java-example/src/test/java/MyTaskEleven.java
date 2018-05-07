import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MyTaskEleven {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/");
    }

    @Test
    public void myTestEleven() {

        // register new
        driver.findElement(By.cssSelector("[name=login_form] a")).click();
        driver.findElement(By.cssSelector("[name=firstname]")).sendKeys("Name");
        driver.findElement(By.cssSelector("[name=lastname]")).sendKeys("Surname");
        driver.findElement(By.cssSelector("[name=address1]")).sendKeys("Lenin street 1");
        driver.findElement(By.cssSelector("[name=postcode]")).sendKeys("22000");
        driver.findElement(By.cssSelector("[name=city]")).sendKeys("Sin City");

        WebElement countryDropdown = driver.findElement(By.cssSelector("[name=country_code]"));
        Select country = new Select(countryDropdown);
        country.selectByVisibleText("United States");

        WebElement stateDropdown = driver.findElement(By.cssSelector("select[name=zone_code]"));
        Select state = new Select(stateDropdown );
        state.selectByValue("AL");
        String emailID = generateEmail();
        driver.findElement(By.cssSelector("[name=email]")).sendKeys(emailID);
        driver.findElement(By.cssSelector("[name=phone]")).sendKeys("+375-17-1234567");
        driver.findElement(By.cssSelector("[name=password]")).sendKeys("password");
        driver.findElement(By.cssSelector("[name=confirmed_password]")).sendKeys("password");
        driver.findElement(By.cssSelector("button[name=create_account]")).click();

        // logout
        driver.findElement(By.cssSelector("div#box-account li:nth-child(4) a")).click();

        // login with newly registered emailID
        driver.findElement(By.cssSelector("[name=email]")).sendKeys(emailID);
        driver.findElement(By.cssSelector("[name=password]")).sendKeys("password");
        driver.findElement(By.cssSelector("[name=login]")).click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div.notice.success"))));
        Assert.assertTrue((driver.findElement(By.cssSelector("div.notice.success")).getText().contains("You are now logged in")));
    }

    String generateEmail() {
        return "aaa" + System.currentTimeMillis() + "@hotmail.com";
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
