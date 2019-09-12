package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {
    public  static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver page) {
        driver = page;
    }

    public Utils(WebDriver driver) {
        setDriver(driver);
    }

    public static void waitElementToBeClickable (WebElement element){
        WebDriverWait wait = new WebDriverWait(getDriver(), Static.TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static boolean elementIsPresent(By selector) {
        try {
            getDriver().findElement(selector);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public static void waitElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Static.TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitSelectorToBeVisible(By selector) {
        return (new WebDriverWait(getDriver(), Static.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public static WebElement waitSelectorToBeClickable(By selector) {
        return (new WebDriverWait(getDriver(), Static.TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(selector));
    }

    public static WebElement waitPresenceOfElement(By selector) {
        return (new WebDriverWait(getDriver(), Static.TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(selector));
    }

    public static void waitTitle(String title) {
        (new WebDriverWait(getDriver(), Static.TIMEOUT)).until(ExpectedConditions.titleIs(title));
    }

    public static void scrollUntilElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,50)");
    }

    public static void sendKeysElement(WebElement element, String id, String value){
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("window.document.getElementById('"+id+"').click();", element);
        executor.executeScript("window.document.getElementById('"+id+"').value ="+value+";", element);
    }

    public static void clickElementWithJs(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    public static void mouseClickByLocator( WebElement element) {
        Actions builder = new Actions(getDriver());
        builder.moveToElement( element ).click( element );
        builder.perform();
    }

    public static void returnPreviousPage(){
        getDriver().navigate().back();
    }

    public static void waitForPageLoadComplete(WebDriver driver) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Static.TIMEOUT);
        wait.until(driver1 -> String
                .valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState"))
                .equals("complete"));
    }

    public static boolean containsIgnoreCase(String str, String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());
    }

}
