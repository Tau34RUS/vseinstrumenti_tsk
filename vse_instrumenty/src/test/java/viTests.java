import org.junit.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
public class viTests {

    public static Methods methods;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        methods = new Methods(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void mainPageWindowText() {
        methods.getWindowTitle();
    }

    @Test
    public void clickOnRegions() {methods.clickRegions();}

    @Test
    public void addToCartSale() {methods.addToCartSale();}

    @Test
    public void rndCity() {
        methods.clickRegions();
        methods.rndDeliveryCity();
    }

    @Test
    public void addToCartSalesItem() {methods.addToCartSalesItem();}

    @Test
    public void getHRPhone() {methods.getHRPhone();}

}