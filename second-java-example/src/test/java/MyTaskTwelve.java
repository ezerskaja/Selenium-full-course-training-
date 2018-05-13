import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyTaskTwelve {
    private WebDriver driver;
    private WebDriverWait wait;
    File image = new File("images/ball.jpg");

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/admin/");
        WebElement u = driver.findElement(By.name("username"));
        u.sendKeys("admin");
        WebElement p = driver.findElement(By.name("password"));
        p.sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void myTestTwelve() {

        WebElement catalogMenuElement = driver.
                findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(2) > a:nth-child(1)"));
        catalogMenuElement.click();

        List<WebElement> buttons  = driver.findElements(By.cssSelector(".button"));
        for (WebElement button : buttons) {
            if (button.getAttribute("textContent").contains("Add New Product")) {
                button.click();
            }
        }

        // general tab
        clickRadioButton(driver.findElements(By.cssSelector("[name=status]")),"Enabled");
        driver.findElement(By.cssSelector("[name*=name]")).sendKeys("New rubber ball");
        driver.findElement(By.cssSelector("[name=code]")).sendKeys("New rubber ball code");
        clickCheckBox(driver.findElements(By.cssSelector("[name*=product_groups]")),"Unisex");
        driver.findElement(By.cssSelector("[name=quantity]")).sendKeys("3");
        driver.findElement(By.cssSelector("input[name*=new_images]")).sendKeys(image.getAbsolutePath());
        driver.findElement(By.cssSelector("[name=date_valid_from]")).sendKeys(Keys.HOME + "01012018");
        driver.findElement(By.cssSelector("[name=date_valid_to]")).sendKeys(Keys.HOME + "01012019");

        //info tab
        driver.findElement(By.cssSelector("ul.index li:nth-child(2)")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[name=manufacturer_id]"))));
        Select selectManufacturer = new Select(driver.findElement(By.cssSelector("[name=manufacturer_id]")));
        selectManufacturer.selectByValue("1");
        Select selectSupplier = new Select(driver.findElement(By.cssSelector("[name=supplier_id]")));
        //selectSupplier.selectByValue("1");
        driver.findElement(By.cssSelector("[name=keywords]")).sendKeys("New rubber ball");
        driver.findElement(By.cssSelector("[name*=short_description]")).sendKeys("New rubber ball");
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("New rubber ball");
        driver.findElement(By.cssSelector("[name*=head_title]")).sendKeys("New rubber ball");
        driver.findElement(By.cssSelector("[name*=meta_description]")).sendKeys("New rubber ball");

        //prices tab
        driver.findElement(By.cssSelector("ul.index li:nth-child(4)")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[name=purchase_price]"))));
        driver.findElement(By.cssSelector("[name=purchase_price]")).sendKeys("5");
        Select selectCurrency = new Select(driver.findElement(By.cssSelector("[name=purchase_price_currency_code]")));
        selectCurrency.selectByValue("USD");
        driver.findElement(By.cssSelector("[name*=USD]")).sendKeys("5.5");
        driver.findElement(By.cssSelector("[name*=EUR]")).sendKeys("4.5");

        driver.findElement(By.cssSelector("button[name=save]")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div.notice.success"))));

        catalogMenuElement = driver.
                findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(2) > a:nth-child(1)"));
        catalogMenuElement.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("table.dataTable"))));

        boolean newProductExist = false;
        List<WebElement> productLinks  = driver.findElements(By.cssSelector("table.dataTable a[href*=product_id]"));
        for (WebElement productLink : productLinks) {
            if (productLink.getAttribute("textContent").contains("New rubber ball")) {
                newProductExist = true;
            }
        }

        Assert.assertTrue("New product is not found in catalog", newProductExist);
    }

    void clickRadioButton (List<WebElement> radioButtons, String radioButtonText) {
        for (WebElement radioButton : radioButtons) {
            if (radioButton.getAttribute("textContent").contains(radioButtonText)) {
                radioButton.click();
            }
        }
    }

    void clickCheckBox (List<WebElement> checkBoxes, String checkBoxText) {
        for (WebElement checkBox : checkBoxes) {
            if (checkBox.getAttribute("textContent").contains(checkBoxText)) {
                checkBox.click();
            }
        }
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
