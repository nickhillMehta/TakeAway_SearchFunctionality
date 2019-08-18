/**
 * Created by mehtani on 18.08.2019.
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import sun.rmi.runtime.Log;

public class SearchResults_POM {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "topbar__title-container")
    WebElement topBarAdd_lbl;

    @FindBy(id = "kitchen-types")
    WebElement kitchentypes_ribbon;


    public SearchResults_POM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // This initElements method will create all WebElements
        wait = new WebDriverWait(driver, 7);
    }

    boolean is_topBarVisibile() {
        wait.until(ExpectedConditions.visibilityOf(topBarAdd_lbl));
        System.out.println("Inside Visibility Check");
        if (topBarAdd_lbl.isDisplayed()) {
            return true;
        }

        return false;
    }

    boolean is_KitchenTypeVisibile() {
        wait.until(ExpectedConditions.visibilityOf(kitchentypes_ribbon));
        System.out.println("Inside Visibility Check");
        if (kitchentypes_ribbon.isDisplayed()) {
            return true;
        }

        return false;
    }

    boolean do_addressMatches(String address) {
        if (topBarAdd_lbl.getText().contains(address)) {
            return true;
        }

        return false;

    }

}
