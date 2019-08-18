/**
 * Created by MehtaNikhil on 16.08.2019.
 */

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.*;

import java.util.function.Function;

import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;


import static java.util.concurrent.TimeUnit.SECONDS;

public class HomePage_Search_POM {
    WebDriver driver;

    @FindBy(name = "mysearchstring")
    WebElement searchString_txtbox;

    @FindBy(id = "submit_deliveryarea")
    WebElement find_btn;
    @FindBy(id = "ideliveryareaerror")
    WebElement area_err;
    WebDriverWait wait;


    public HomePage_Search_POM(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 3);
        PageFactory.initElements(driver, this); // This initElements method will create all WebElements

    }
    // Set address/post in find textbox

    public void setAddressText(String searchStr) {
        searchString_txtbox.clear();
        searchString_txtbox.sendKeys(searchStr);
    }

    // Click on find button

    public void clickFind() {

        find_btn.click();

    }


    boolean is_validationTextPresent() {

        try {
            FluentWait<WebDriver> Fwait = new FluentWait<WebDriver>(driver)
                    .withTimeout(6, TimeUnit.SECONDS)
                    .pollingEvery(2, TimeUnit.SECONDS);
            //  .ignoring(NoSuchElementException.class).ignoring(java.lang.NullPointerException.class);

            WebElement err_text = Fwait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {

                    WebElement element = driver.findElement(By.id("ideliveryareaerror"));
                    System.out.println("Polling..");
                    if (element.isDisplayed()) {
                        System.out.println("Returning errorText element:");
                        return element;

                    } else {
                        System.out.println("Returning null element:");
                        return null;
                    }
                }
            });
            if (err_text != null) {
                System.out.println("Validation text  present:" + err_text.getText());
                return true;
            } else {
                System.out.println("Validation text not  present:");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public void findbyAddressOrPost(String strSearch) {

        // Fill search textbox

        this.setAddressText(strSearch);
        this.showResults(strSearch);


    }

    public boolean showResults(String textToSelect) {
        try {
            WebElement autoOptionsList = driver.findElement(By.id("iautoCompleteDropDownContent"));
            wait.until(ExpectedConditions.visibilityOf(autoOptionsList));

            List<WebElement> optionsToSelect = autoOptionsList.findElements(By.xpath("//*[@id=\"iautoCompleteDropDownContent\"]/a"));
            if (optionsToSelect.size() == 1) {
                System.out.println("When result =" + optionsToSelect.size());
                searchString_txtbox.sendKeys(Keys.ENTER);
                //clickFind();
                if (is_validationTextPresent()) {
                    System.out.println("Inside validation text: result 1");
                    return false;
                }
                return true;

            } else {
                for (WebElement option : optionsToSelect) {
                    System.out.println(option.getText());
                    if (option.getText().equals(textToSelect)) {
                        Reporter.log("Trying to select: " + textToSelect);
                        option.click();   //this is for selecting from the list
                        //clickFind();        //This is for using search button functionality
                        return true;
                    }
                }
                clickFind();
                if (is_validationTextPresent()) {
                    return false;
                } else return true;
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getStackTrace());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return false;
    }


}

