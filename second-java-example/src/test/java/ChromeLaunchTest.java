import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ChromeLaunchTest {
    private WebDriver driver;

    @Before
            public void start() {

        //ChromeOptions options = new ChromeOptions();
        //options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        //options.addArguments("start-maximized");
        //WebDriver driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    @Test
    public void mySecondTest() {
        driver.get("http://newbor.by/");

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
