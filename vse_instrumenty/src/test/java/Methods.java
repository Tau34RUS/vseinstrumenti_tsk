import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Methods {

    public Logger log = Logger.getLogger("methods");

    public WebDriver driver;
    public Methods(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    private String generateXPATH(WebElement childElement, String current) {
        String childTag = childElement.getTagName();
        if(childTag.equals("html")) {
            return "/html[1]"+current;
        }
        WebElement parentElement = childElement.findElement(By.xpath(".."));
        List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
        int count = 0;
        for(int i=0;i<childrenElements.size(); i++) {
            WebElement childrenElement = childrenElements.get(i);
            String childrenElementTag = childrenElement.getTagName();
            if(childTag.equals(childrenElementTag)) {
                count++;
            }
            if(childElement.equals(childrenElement)) {
                return generateXPATH(parentElement, "/" + childTag + "[" + count + "]"+current);
            }
        }
        return null;
    }

    @FindBy(className = "showMoreRepresent")
    private WebElement regions;

    @FindBy(className = "retail-sale-buy")
    private WebElement addToCartSale;

    public void getWindowTitle() {
        driver.get("https://volgograd.vseinstrumenti.ru/");
        String windowTitle = driver.getTitle();
        log.info("Current Tab Title: " + windowTitle);
    }

    public void clickRegions() {
        driver.get("https://volgograd.vseinstrumenti.ru/");
        regions.click();
    }

    public void addToCartSale() {
        driver.get("https://volgograd.vseinstrumenti.ru/instrument/pnevmoinstrument/pnevmosteplery/matrix/matrix_nejler_pnevmaticheskij_dlya_gvozdej_ot_20_do_50_mm_57410/");
        addToCartSale.click();
    }

    public void rndDeliveryCity() {
        List<WebElement> allDeliveryCities = driver.findElements(By.className("deliveryCourierConteiner"));
        List<WebElement> allBoldCities = driver.findElements(By.className("fs-14"));
        Random rand = new Random();
        int randomNum = rand.nextInt((allDeliveryCities.size() - allBoldCities.size())) + allBoldCities.size();
        allDeliveryCities.get(randomNum).click();
    }

    public void addToCartSalesItem() {
        driver.get("https://www.vseinstrumenti.ru/instrument/gravery/akkumulyatornye/");
        WebElement bestPriseMark = driver.findElement(By.className("nameplates"));
        String bestPriceMarkXPath = generateXPATH(bestPriseMark, "");
        String buyButton = bestPriceMarkXPath.substring(0, bestPriceMarkXPath.length() - 25) + "/div[2]/div[1]/div[9]/a";
        driver.findElement(By.xpath(buyButton)).click();

    }

    public void getHRPhone() {
        driver.get("https://www.vseinstrumenti.ru/contacts/1.html");
        WebElement hrColumn = driver.findElement(By.xpath("//*[text()=' Отдел подбора персонала ']"));
        String hrXPath = generateXPATH(hrColumn, "");
        String hrPhone = hrXPath.substring(0, hrXPath.length() - 2) + "2]/div[1]";
        log.info("HR Phone: " + driver.findElement(By.xpath(hrPhone)).getText());
    }

}

