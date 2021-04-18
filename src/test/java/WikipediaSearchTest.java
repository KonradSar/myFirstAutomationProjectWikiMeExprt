import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WikipediaSearchTest {
    private WebDriver driver;
    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", Variables.WEBDRIVERPATH);
        driver = new ChromeDriver();
        driver.navigate().to(Variables.WIKIWEBSITE);

    }
    @Test
    public void wikiTest(){
        Assert.assertEquals(driver.getTitle(), Variables.WIKIFREETITLE);
        driver.findElement(By.linkText(Variables.ABOUTWIKI)).click();
        driver.findElement(By.xpath(Variables.XPATHTOWIKISEARCH)).sendKeys("Poznan");
        driver.findElement(By.cssSelector("#searchInput")).sendKeys("Gliwice");
        driver.findElement(By.id("searchInput")).sendKeys("Warszawa");
        driver.findElement(By.id("searchButton")).click();

    }
    @After
    public void tearDown(){
        driver.quit();

    }
}
