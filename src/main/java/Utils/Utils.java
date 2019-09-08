package Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Random;

public class Utils {
    protected static WebDriver driver;

    public Utils(WebDriver driver) {
        this.driver = driver;
    }

    public static void waitElementToBeClickable (WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Static.TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static boolean elementIsPresent(By selector) {
        try {
            driver.findElement(selector);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public static void waitElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Static.TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitSelectorToBeVisible(By selector) {
        return (new WebDriverWait(driver, Static.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public static WebElement waitSelectorToBeClickable(By selector) {
        return (new WebDriverWait(driver, Static.TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(selector));
    }

    public static WebElement waitPresenceOfElement(By selector) {
        return (new WebDriverWait(driver, Static.TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(selector));
    }

    public static void waitTitle(String title) {
        (new WebDriverWait(driver, Static.TIMEOUT)).until(ExpectedConditions.titleIs(title));
    }

    public static void scrollUntilElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,50)");
    }

    public static void sendKeysElement(WebElement element, String id, String value){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("window.document.getElementById('"+id+"').click();", element);
        executor.executeScript("window.document.getElementById('"+id+"').value ="+value+";", element);
    }

    public static void clickElementWithJs(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public static void mouseClickByLocator( WebElement element) {
        Actions builder = new Actions(driver);
        builder.moveToElement( element ).click( element );
        builder.perform();
    }

    public static void returnPreviousPage(){
        driver.navigate().back();
    }

    public static int returnRandomNumber(String[] strArray){
        Random r = new Random();
        return r.nextInt(strArray.length);
    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();

        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().equals(fileName)) {
                // File has been found, it can now be deleted:
                dirContents[i].delete();
                return true;
            }
        }
        return false;
    }
    public static void isjQueryLoaded(WebDriver driver) {
        System.out.println("Waiting for ready state complete");
        (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                JavascriptExecutor js = (JavascriptExecutor) d;
                String readyState = js.executeScript("return document.readyState").toString();
                System.out.println("Ready State: " + readyState);
                return (Boolean) js.executeScript("return !!window.jQuery && window.jQuery.active == 0");
            }
        });
    }

    public static void waitForPageLoadComplete(WebDriver driver) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Static.TIMEOUT);
        wait.until(driver1 -> String
                .valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState"))
                .equals("complete"));
    }

}
