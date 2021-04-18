import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class WorkplaceWithEverything {
    private WebDriver driver;
    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna");
        //Robimy to w jednym miejscu, jest to na sztywno dla wszystkich elementów i nie jest dobre dla wyjątków,
        // ponizej dotyczy czasu wyszukiwania wszystkich elementów na stronie:
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }
    @Test
    public void wikiTest(){
        //W konkretnym miejscu ustalamy czas dla drivera i nie jest to globalne, pełna kontrola działania:
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeSelected(driver.findElement(By.id(""))));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id(""))));

        Assert.assertEquals(driver.getTitle(), "Wikipedia, wolna encyklopedia");
        driver.findElement(By.linkText("O Wikipedii")).click();
//        verifyIfElementExists(driver, By.id("safaffs"));
        Assert.assertEquals(true, verifyIfElementExists(driver, By.id("sfsdfsdfsf")));
        driver.findElement(By.id("searchInput")).sendKeys("Warszawa");
        driver.findElement(By.id("searchButton")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("searchButton")).click();
        //ScreenShoot
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

    }

    public static boolean verifyIfElementExists(WebDriver webDriver, By by){
        try{
            webDriver.findElement(by);
            return true;
        }catch(NoSuchElementException e){
            System.out.println("Such element not found");
            return false;

        }
    }
    @After
    public void tearDown(){
        driver.quit();

    }
}
