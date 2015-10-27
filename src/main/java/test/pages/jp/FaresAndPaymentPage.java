package test.pages.jp;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import test.driver.manager.WebDriverManager;
import test.pages.jp.common.BasePage;

import java.util.List;

/**
 * Created by balajiravivarman on 09/10/2014.
 */
public class FaresAndPaymentPage extends BasePage {

    @FindBy(how = How.ID , using = "From")
    private WebElement from;

    @FindBy(how = How.ID , using = "To")
    private WebElement to;

    @FindBy(how = How.ID , using = "PassengerType")
    private WebElement passengerType;

    @FindBy(how = How.CLASS_NAME , using = "primary-button")
    private WebElement checkFare;

    @FindBy(how = How.CSS , using = ".label-no-height")
    private List<WebElement> faresResults;

    public static final String OYSTER_FARE = "oyster";
    public static final String CASH_FARE = "cash";
    public static final String FARE_FINDER = "fares-and-payments/fares/single-fare-finder";


    public FaresAndPaymentPage(WebDriver driver) {
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 30);
        PageFactory.initElements(finder, this);
        this.driver = driver;
    }

    public WebDriver load() {
        return WebDriverManager.getSpecificSection(driver, FARE_FINDER);
    }

    public void findFare(String fromStation, String toStation) {
          from.sendKeys(fromStation);
          to.sendKeys(toStation);
          checkFare.submit();

    }

    public boolean checkFares() {
        boolean overAllCheckStatus = Boolean.FALSE;
        String fareTypes = "";
        if (faresResults.size() > 0) {
            for (int i=0; i < faresResults.size(); i++) {
                fareTypes = fareTypes + " " + faresResults.get(i).getText();
            }
            if (StringUtils.containsIgnoreCase(fareTypes, OYSTER_FARE) || StringUtils.containsIgnoreCase(fareTypes, CASH_FARE)) {
                overAllCheckStatus = Boolean.TRUE;
            }
        }
        return overAllCheckStatus;
    }

    public boolean checkWeAreAtSFFPage() {
        String title = driver.getTitle();
        return title.equals("Single fare finder - Transport for London");
    }
}
