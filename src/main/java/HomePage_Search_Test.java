import java.io.IOException;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.asserts.Assertion;
import org.openqa.selenium.TakesScreenshot;

/**
 * Created by mehtani on 16.08.2019.
 */
public class HomePage_Search_Test {

    WebDriver driver = null;
    HomePage_Search_POM homepage;
    SearchResults_POM searchResults;

    @Test(dataProvider = "data-provider", testName = "HomePage_Search_Test")
    public void SearchAddressTest(String search_str, boolean expectedResult, String browser, String Url) {


        browser_Selection browser_selection = new browser_Selection(driver);
        driver = browser_selection.setBrowser(browser, Url);


        homepage = new HomePage_Search_POM(driver);
        searchResults = new SearchResults_POM(driver);
        boolean search_success = false;
        //      vl = new Veterinarians_List(driver);
        try {
            Reporter.log("--Testing Search functionality with Parameters::");
            homepage.setAddressText(search_str);
            Reporter.log("--Search Address set in search address textbox::" + search_str);
            search_success = homepage.showResults(search_str);
            System.out.println("Search success:" + search_success);

            if (search_success) {
                Reporter.log("--Results for search address::");
                Assert.assertEquals(true, searchResults.is_topBarVisibile());
                Reporter.log("--Assertion Passes:Address visible in Top bar on Result page::");
                Assert.assertEquals(true,searchResults.do_addressMatches(search_str));
                Reporter.log("--Assertion Passes:Search String visible in Top bar on Result page::");
                Assert.assertEquals(true, searchResults.is_KitchenTypeVisibile());
                Reporter.log("--Assertion Passes:Kitchen types visible in Kitchen Ribbon on Result page::");

            }
            Assert.assertEquals(search_success, expectedResult);

            Reporter.log("--Expected Result matches Actual Result::");


        } catch (AssertionError e) {
            Reporter.log("--Assertion Failed::");
        } catch (Exception ex) {
            Reporter.log("--Element Not Found::");
            TakeScreenshot ts = new TakeScreenshot(driver);
            ts.capture("screenshot", "TC.Search_Address");
            System.out.println("In case of any element not found:");


        }
        System.out.println("**Exiting Test Case**");

    }


    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{"Amsterdam-Centrum, Amsterdam", true, "Chrome", "https://www.thuisbezorgd.nl/"}, {"1025", true, "Firefox", "https://www.thuisbezorgd.nl/"}, {"utrecht", false, "Chrome", "https://www.thuisbezorgd.nl/"}, {"xqplghtzd", false, "Firefox", "https://www.thuisbezorgd.nl/"}, {"1000", false, "firefox", "https://www.thuisbezorgd.nl/"},{"1222", true, "chrome", "https://www.thuisbezorgd.nl/"}};
    }

    @AfterMethod
    public void destroy() {
        driver.quit();


    }
}
