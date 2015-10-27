package test.steps.fares;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import tfl.driver.manager.WebDriverManager;
import tfl.pages.fares.FaresAndPaymentPage;

import static junit.framework.TestCase.assertTrue;

/**
 * Step definition for Fare Finder
 * Created by balajiravivarman on 09/10/2014.
 */
public class FaresFinderSteps {

    public static final String FARE_FINDER = "fares-and-payments/fares/single-fare-finder";
    private WebDriver driver;
    private FaresAndPaymentPage faresAndPaymentPage;

    @Before
    public void initialise() {
        if (driver == null) {
            driver = WebDriverManager.getDriverDynamic();
        }
        WebDriverManager.getSpecificSection(driver, FARE_FINDER);
        faresAndPaymentPage = new FaresAndPaymentPage(driver);
        driver.manage().window().maximize();
    }


    @Given("A customer is at the single fare finder page$")
    public void is_at_the_single_fare_finder_page() throws Throwable {
        boolean singleFinderPage = faresAndPaymentPage.checkWeAreAtSFFPage();
        Assert.assertTrue("We are not in single fare finder page", singleFinderPage);
    }

    @When("^I check fares between (.*) and (.*)$")
    public void he_enters_from_station_and_to_station(String from, String to) throws Throwable {
        faresAndPaymentPage.findFare(from, to);
    }

    @Then("^the fares are displayed$")
    public void he_is_provided_with_single_fares() throws Throwable {
        assertTrue(faresAndPaymentPage.checkFares());
    }

    @After
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}
