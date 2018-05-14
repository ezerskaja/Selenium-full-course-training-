import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;


public class MyTaskThirteen {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myTestThirteen() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/");

        WebElement element;

        for (int i=1; i<=3; i++) {

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div#box-most-popular li:nth-child(1)"))));

            element = driver.findElement(By.cssSelector("div#box-most-popular li:nth-child(1)"));
            element.click();

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[name=add_cart_product"))));

            if ((driver.findElements(By.cssSelector("select[name='options[Size]']")).size() > 0 )) {
                Select selectOptions = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
                selectOptions.selectByIndex(1);
            }

            element = driver.findElement(By.cssSelector("button[name=add_cart_product"));
            element.click();

            element = driver.findElement(By.cssSelector("div#cart .quantity"));

            wait.until(textToBePresentInElement(element, String.valueOf(i)));
            driver.findElement(By.cssSelector(".general-0")).click();
        }

        driver.findElement(By.cssSelector("a.link[href*=checkout]")).click();

        List<WebElement> itemsForms = driver.findElements(By.cssSelector("form[name=cart_form]"));

        for (int i=1; i<=itemsForms.size(); i++) {

            WebElement itemForm = driver.findElement(By.cssSelector("form[name=cart_form]"));
            element = itemForm.findElement(By.cssSelector("span"));
            String skuNumber = element.getAttribute("textContent");
            // [SKU: XXXX]
            skuNumber = skuNumber .replace("[SKU: ","").replace("]","");

            List<WebElement> skuTDs  = driver.findElements(By.cssSelector("table.dataTable td.sku"));
            for (WebElement skuTD : skuTDs) {
                if (skuTD.getAttribute("textContent").equals(skuNumber)) {
                    element = skuTD;
                }
            }
            WebElement removeButton = itemForm.findElement(By.cssSelector("button[name='remove_cart_item']"));
            removeButton.click();
            wait.until(stalenessOf(element));
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
