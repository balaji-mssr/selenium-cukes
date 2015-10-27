package tfl.driver.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class WebDriverManager {

	
    public static String url = "http://www.tfl.gov.uk/";
    private static WebDriver driver = null;

    /**
	 * Get driver based on test_map.properties variable env.driver.browser
	 * @return driver driverType
	 */
	public static WebDriver getDriverDynamic() {
		String defaultBrowser = getBrowserToTestOn();
       if (driver == null) {

           if (defaultBrowser.equals("ie")) {
               // set driver location
               setDriverProperty(defaultBrowser, "webdriver.ie.driver");
               DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
               caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
               caps.setCapability(
                       InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                       true);
               driver = new InternetExplorerDriver(caps);

           } else if (defaultBrowser.equals("internetexplorer")) {
               // set driver location
               setDriverProperty(defaultBrowser, "webdriver.ie.driver");
               DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
               caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
               caps.setCapability(
                       InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                       true);
               driver = new InternetExplorerDriver(caps);

           } else {
               switch (defaultBrowser) {
                   case "chrome":
                   case "gc":
                       // //set driver location
                       setDriverProperty(defaultBrowser, "webdriver.chrome.driver");
                       //DesiredCapabilities chrome = DesiredCapabilities.chrome();
                       driver = new ChromeDriver();
                       break;
                   case "headless":
                       driver = getHeadlessRemoteWebDriverInstance();
                       //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                       break;
                   case "htmlunit":
                       //DesiredCapabilities capabilities = DesiredCapabilities.htmlUnitWithJs();
                       DesiredCapabilities capabilities = DesiredCapabilities.htmlUnit();
                       capabilities.setBrowserName("firefox");
                       capabilities.setJavascriptEnabled(true);
                       capabilities.setPlatform(Platform.WIN8);
                       driver = new HtmlUnitDriver(capabilities);
                       break;
                   default:
                       // set driver properties
                       FirefoxProfile profile = new FirefoxProfile();
                       profile.setPreference("javascript.enabled", true);
                       profile.setEnableNativeEvents(true);
                       profile.setPreference("webdriver.load.strategy", "fast"); // As of 2.19. from 2.9 - 2.18 use 'fast'


                       //Allow geo location :
                       profile.setPreference("geo.prompt.testing", true);
                       profile.setPreference("geo.prompt.testing.allow", true);
                       driver = new FirefoxDriver(profile);
                       break;
               }
           }
       }
		return driver;
	}

    /*******************************************************************************
     * Go to the specific section
     * @param driver webDriver type
     * @param pageSection section of the Page
     * @return driver
     *******************************************************************************/
    public static WebDriver getSpecificSection(WebDriver driver, String pageSection){
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.get(url+pageSection);
        return driver;
    }


	/**
	 * Get headless instance
	 * @return driver
	 */
	private static WebDriver getHeadlessRemoteWebDriverInstance() {
		try {
			if (driver == null){
				DesiredCapabilities caps = DesiredCapabilities.phantomjs();
				caps.setJavascriptEnabled(true);
				caps.setCapability("takesScreenshot", false);
				driver = new PhantomJSDriver(caps);
				driver.manage().window().setSize( new Dimension( 1124, 850 ) );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	/**
	 * We need to set chromedriver.exe and ieserverdriver.exe locations
	 * @param defaultBrowser defaultBrowser type
	 * @param whichDriver driver type
	 */
	private static void setDriverProperty(String defaultBrowser,
			String whichDriver) {
		String locationOfDriver = getASpecificProperty("env.browser.location." + defaultBrowser);
		if (locationOfDriver != null) {
			String wid = System.getProperty(whichDriver);
			if (wid == null) {
				// This only needs to be done once
				File file = new File(locationOfDriver);
				System.setProperty(whichDriver, file.getAbsolutePath());
			}
		}
	}

    /****************************************************
     * load the location of the driver
     * @param browser browser type
     * @return locationOfDriver
     */
    private static String getASpecificProperty(String browser) {
        //For Chrome Or IE
        //set the required driver location
        if (browser.equalsIgnoreCase("gc")) {
            return "/usr/local/bin/chromedriver";
        }
        return null;
    }

    /*********************************************
     * default set to firefox
     * if require override the required browser from the
     * system properties
     **********************************************/

    public static String getBrowserToTestOn() {
        return "";
    }
}
