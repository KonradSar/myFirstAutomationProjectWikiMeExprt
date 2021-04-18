import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MediaExpertNumberOfResults extends Variables {
    private WebDriver webDriver;

    @Before
    public void setUp(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver", Variables.WEBDRIVERPATH);
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.navigate().to(Variables.MEDIAEPRTWEBSITE);
    }

    @Test
    public void testWebsite(){
        //wait
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 5);
        //klik na zamknij na akceptacje cookies
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(Variables.XPATHTOCOOKIESCLOSEBTN))).click();
        //klik na sekcje Telewizory i RTV
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(Strings.TVANDRTV)));
        webDriver.findElement(By.linkText(Strings.TVANDRTV)).click();
        //klik na sekcje Telewizory
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Variables.XPATHTOTVBTN)));
        webDriver.findElement(By.xpath(Variables.XPATHTOTVBTN)).click();
        //pobranie elementów listy i sprawdzenie czy jest na stronie 30 elementow
        Assert.assertEquals(true, checIfListSizeIsBiggerThanZero(webDriver, By.xpath(Variables.XPATHTOLISTFORMEDIAEKSPERT)));
        Assert.assertEquals(Strings.THERESNOT30ITEMS, getListSize(webDriver, By.xpath(Variables.XPATHTOLISTFORMEDIAEKSPERT)), 30);
        //klik na rozwijane menu i wybor pozycji
        Select dropdown = new Select(webDriver.findElement(By.xpath(Variables.XPATHTODROPDOWNMENU)));
        dropdown.selectByIndex(2);

    }

    @After
    public void tearDown(){
        webDriver.quit();
    }

    private boolean checIfListSizeIsBiggerThanZero(WebDriver webDriver, By by){
        try{
            boolean isBiggerThanZero = webDriver.findElements(by).size()>0;
            if(isBiggerThanZero){
                return true;
            }else{
                return false;
            }

        }catch (NoSuchElementException e) {
            System.out.println(Strings.NOSUCHLIST);
            return false;
        }
    }

    private int getListSize(WebDriver webDriver, By by){
        return webDriver.findElements(by).size();

    }

//    private boolean doesListConsistOfMaxNumberOfElements(int valueToCompareWith){
//        if(getListSize(webDriver, By.xpath("//div[@class='c-offerBox is-wide  is-available']"))==valueToCompareWith){
//            return true;
//        }else{
//            return false;
//
//        }
//    }


}
