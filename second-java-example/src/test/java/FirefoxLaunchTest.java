import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirefoxLaunchTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        DesiredCapabilities caps = new DesiredCapabilities();
        driver = new FirefoxDriver(caps);
        System.out.println(((HasCapabilities)driver).getCapabilities());
        //FirefoxOptions options = new FirefoxOptions().setLegacy(false);
       // WebDriver driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest(){
        driver.get ("http://newbor.by");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}
