package tfl.pages.common;

import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import tfl.driver.manager.WebDriverManager;

/**
 *
 *Created by balajiravivarman on 09/10/2014.
 */
public class BasePage {
    protected WebDriver driver;

    @Before
    public void setUp() throws Exception {
        if (driver == null) {
            driver = WebDriverManager.getDriverDynamic();
        }
    }
}
