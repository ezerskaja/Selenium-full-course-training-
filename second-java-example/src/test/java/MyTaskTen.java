import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyTaskTen {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
/*
        DesiredCapabilities caps = new DesiredCapabilities();
        driver = new InternetExplorerDriver(caps);
        System.out.println(((HasCapabilities)driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);
*/
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/");
    }

    @Test
    public void myTestTen() {
        List<String> nonMatchParameters = new ArrayList<>();

        WebElement product = driver.findElement(By.cssSelector("div#box-campaigns li.product"));

        // Main page data gathering

        String productNameMPage = product.findElement(By.cssSelector(".name")).getAttribute("textContent");

        String regularProductPriceMPage = product.
                findElement(By.cssSelector(".regular-price")).getAttribute("textContent");

        String discountProductPriceMPage = product.
                findElement(By.cssSelector(".campaign-price")).getAttribute("textContent");

        String regularPriceStyleMPage = product.
                findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration-line");

        String regularPriceColorMPage = product.
                findElement(By.cssSelector(".regular-price")).getCssValue("color");

        String[] rgbArray = parseRGB(regularPriceColorMPage);

        int r = Integer.parseInt(rgbArray[0]);
        int g = Integer.parseInt(rgbArray[1].trim());
        int b = Integer.parseInt(rgbArray[2].trim());

        if ( (r != g) || (g != b) || !regularPriceStyleMPage.equals("line-through") ) {
            nonMatchParameters.add("Colour of regular price has not equal R, G or B components " +
                    "or is not StrikeThrough on the main page.");
        }

        String discountPriceBoldMPage = product.
                findElement(By.cssSelector(".campaign-price")).getCssValue("font-weight");

        String discountProductColourMPage = product.
                findElement(By.cssSelector(".campaign-price")).getCssValue("color");

        rgbArray = parseRGB(discountProductColourMPage);

        g = Integer.parseInt(rgbArray[1].trim());
        b = Integer.parseInt(rgbArray[2].trim());

        if ( (g != b) || (g != 0) || !isBold(discountPriceBoldMPage)) {
            nonMatchParameters.add("Discount price is not bold or is not red on the main page.");
        }

        Dimension regularPriceSize = product.
                findElement(By.cssSelector(".regular-price")).getSize();
        Dimension discountPriceSize = product.
                findElement(By.cssSelector(".campaign-price")).getSize();

        if (( regularPriceSize.getHeight() >= discountPriceSize.getHeight()) ||
                (regularPriceSize.getWidth() >= discountPriceSize.getWidth())) {
            nonMatchParameters.add("Discount price is not bigger than regular price on the main page.");
        }

        product.click();
        product = driver.findElement(By.cssSelector("div#box-product"));

        // Product page data gathering

        String productNamePPage = product.
                findElement(By.cssSelector("h1.title")).getAttribute("textContent");

        String regularProductPricePPage = product.
                findElement(By.cssSelector(".regular-price")).getAttribute("textContent");

        String discountProductPricePPage = product.
                findElement(By.cssSelector(".campaign-price")).getAttribute("textContent");

        if (!productNameMPage.equals(productNamePPage)) {
            nonMatchParameters.add("Product title is not equal on the main and on the product pages.");
        }

        if (!regularProductPriceMPage.equals(regularProductPricePPage)
                || !discountProductPriceMPage.equals(discountProductPricePPage)) {
            nonMatchParameters.add("Regular or discount prices are not equal on the main and on the product pages.");
        }

        String regularPriceStylePPage = product.
                findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration-line");

        String regularPriceColorPPage = product.
                findElement(By.cssSelector(".regular-price")).getCssValue("color");

        rgbArray = parseRGB(regularPriceColorPPage);

        r = Integer.parseInt(rgbArray[0]);
        g = Integer.parseInt(rgbArray[1].trim());
        b = Integer.parseInt(rgbArray[2].trim());

        if ( (r != g) || (g != b) || !regularPriceStylePPage.equals("line-through") ) {
            nonMatchParameters.add("Colour of regular price has not equal R, G or B components " +
                    "or is not StrikeThrough on the product page.");
        }

        String discountPriceBoldPPage = product.
                findElement(By.cssSelector(".campaign-price")).getCssValue("font-weight");

        String discountProductColourPPage = product.
                findElement(By.cssSelector(".campaign-price")).getCssValue("color");

        rgbArray = parseRGB(discountProductColourPPage);

        g = Integer.parseInt(rgbArray[1].trim());
        b = Integer.parseInt(rgbArray[2].trim());

        if ( (g != b) || (g != 0) || !isBold(discountPriceBoldPPage)) {
            nonMatchParameters.add("Discount price is not bold or is not red on the product page.");
        }

        regularPriceSize = product.
                findElement(By.cssSelector(".regular-price")).getSize();
        discountPriceSize = product.
                findElement(By.cssSelector(".campaign-price")).getSize();

        if (( regularPriceSize.getHeight() >= discountPriceSize.getHeight()) ||
                (regularPriceSize.getWidth() >= discountPriceSize.getWidth())) {
            nonMatchParameters.add("Discount price is not bigger than regular price on the product page.");
        }

    }

    boolean isBold (String fontWeight) {
        return fontWeight.equals("bold") || fontWeight.equals("bolder") || (Integer.parseInt(fontWeight) >= 700);
    }

    String[] parseRGB (String colour) {
        return colour.
                replace("rgba(","").
                replace("rgb(","").
                replace(")","").
                split(",");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
