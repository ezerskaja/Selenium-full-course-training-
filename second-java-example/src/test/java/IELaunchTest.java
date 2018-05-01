import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IELaunchTest {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        //driver = new InternetExplorerDriver();
        DesiredCapabilities caps = new DesiredCapabilities();
        driver = new InternetExplorerDriver(caps);
        System.out.println(((HasCapabilities)driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);
        //caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        //WebDriver driver = new InternetExplorerDriver(caps);
        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void IELaunchTest() {
        driver.get("http://newbor.by/");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
