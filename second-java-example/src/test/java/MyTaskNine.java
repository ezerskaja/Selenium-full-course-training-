import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyTaskNine {
    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.cssSelector("[name=username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=password]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=login]")).click();
    }

    @Test
    public void myTaskNineFirstA() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List <WebElement> countriesLinks = driver.findElements(By.cssSelector("table.dataTable tr.row td:nth-child(5) a"));

        List<String> countriesNames = new ArrayList<>();
        for (WebElement countryLink : countriesLinks) {
            countriesNames.add(countryLink.getText());
        }

        List<String> countriesNamesSorted = new ArrayList<>(countriesNames);
        countriesNamesSorted.sort(Comparator.naturalOrder());

        Assert.assertTrue("Countries are not sorted", countriesNames.equals(countriesNamesSorted));

    }

    @Test
    public void myTaskNineFirstB() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        List<WebElement> countriesRows = driver.findElements(By.cssSelector("table.dataTable tr.row"));
        List<String> links = new ArrayList<>();

        for (WebElement row : countriesRows) {
            WebElement countryLink = row.findElement(By.cssSelector("td:nth-child(5) a"));
            WebElement countryZones = row.findElement(By.cssSelector("td:nth-child(6)"));

            int ctryZones = Integer.parseInt(countryZones.getText());

            if (ctryZones > 0) {
                links.add(countryLink.getAttribute("href"));
            }
        }

        List<String> countriesWithUnsortedZones = new ArrayList<>();

        for (String link : links) {
            driver.get(link);
            List<WebElement> countryZones = driver.findElements(By.cssSelector("table#table-zones td:nth-child(3)"));

            List<String> zoneNames = new ArrayList<>();
            for (WebElement countryZone : countryZones) {
                if (countryZone.getAttribute("type") == "hidden") zoneNames.add(countryZone.getText());
            }

            List<String> zoneNamesSorted = new ArrayList<>(zoneNames);
            zoneNamesSorted.sort(Comparator.naturalOrder());

            if (! zoneNames.equals(zoneNamesSorted)) {
                countriesWithUnsortedZones.add(link.substring(link.length()-2));
            }
        }
        Assert.assertTrue("Country codes of countries which have unsorted zones 1st task: " + countriesWithUnsortedZones.toString(), countriesWithUnsortedZones.size() == 0);
    }

    @Test
    public void myTaskNineSecond() {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        List<WebElement> countryNameCells = driver.
                findElements(By.cssSelector("table.dataTable tr.row td:nth-child(3) a"));

        List<String> links = new ArrayList<>();

        for (WebElement countryNameCell : countryNameCells) {
            links.add(countryNameCell.getAttribute("href"));
        }

        List<String> countriesWithUnsortedZones = new ArrayList<>();
        for (String link : links) {
            driver.get(link);
            List<WebElement> zones = driver.
                    findElements(By.cssSelector("table#table-zones td:nth-child(3) option[selected]"));

            List<String> zoneNames = new ArrayList<>();
            for (WebElement zone : zones) {
                zoneNames.add(zone.getText());
            }

            List<String> zoneNamesSorted = new ArrayList<>(zoneNames);
            zoneNamesSorted.sort(Comparator.naturalOrder());

            zoneNamesSorted.sort(Comparator.naturalOrder());

            if (! zoneNames.equals(zoneNamesSorted)) {
                countriesWithUnsortedZones.add(link.substring(link.length()-2));
            }

        }

        Assert.assertTrue("Country codes of countries which have unsorted zones 2nd task: " + countriesWithUnsortedZones.toString(), countriesWithUnsortedZones.size() == 0);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
