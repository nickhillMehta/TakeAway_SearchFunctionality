import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by mehtanikhil on 03.08.2019.
 */
public class browser_Selection {

    WebDriver driver;


    browser_Selection(WebDriver driver) {
        this.driver = driver;

    }

    public WebDriver setBrowser(String Brsr, String url) {
        char browser;

        if (Brsr.equalsIgnoreCase("Chrome")) {
            browser = 'C';
        } else if (Brsr.equalsIgnoreCase("IE")) {
            browser = 'I';
        } else if (Brsr.equalsIgnoreCase("FIREFOX")) {
            browser = 'F';
        } else {
            browser = 'Z';
        }

        switch (browser) {

            case 'I':
                // initiating ie driver
                System.out.println("Tests running on internet explorer");
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\driver\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                driver.get(url);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
                System.out.println("Tests running on IE");
                break;

            case 'F':
                // initiating the firefox driver

                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\driver\\geckodriver.exe");
                driver = new FirefoxDriver();
                driver.get(url);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                System.out.println("Tests running on firefox");

                break;

            case 'C':
                // initiating the firefox driver
                // System.setProperty("webdriver.chrome.driver", "C:\\temp\\chromedriver_win32V63\\chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
                driver = new ChromeDriver();
                driver.get(url);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                System.out.println("Tests running on Chrome");

                break;

            default:
                // default behavior

                System.out.println("Please enter an appropriate browser");
                break;
        }

        return driver;
    }
}
