import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;

public class FirefoxNightlyLaunchTest {

    private WebDriver driver;


    @Before
    public void start() {
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Firefox Nightly\\firefox.exe")));
        driver = new FirefoxDriver(options);
        System.out.println(((HasCapabilities) driver).getCapabilities());
    }

    @Test
    public void myFirefoxTest() {
        driver.navigate().to("http://newbor.by");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

