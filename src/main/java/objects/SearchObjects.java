package objects;

import elements.SearchElements;
import utils.Utils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchObjects extends SearchElements {

    private static final Logger log = LogManager.getLogger(SearchObjects.class);
    private static WebDriver driver;

    private static WebDriver getDriver() {
        return driver;
    }

    private static void setDriver(WebDriver page) {
        driver = page;
    }
    private static Utils utils;

    public SearchObjects(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(driver, this);
        utils = new Utils(getDriver());
    }

    public void googleSearch(String word) {
            utils.waitForPageLoadComplete(getDriver());
            utils.waitElementToBeClickable(googleSearchBox);
            googleSearchBox.sendKeys(word);
            googleSearchBox.sendKeys(Keys.ENTER);
    }

    public void bingSearch(String word) {
        utils.waitForPageLoadComplete(getDriver());
        utils.waitElementToBeClickable(bingSearchBox);
        bingSearchBox.sendKeys(word);
        bingSearchBox.sendKeys(Keys.ENTER);
    }

    public void bingSearchMobile(String word) {
        utils.waitForPageLoadComplete(getDriver());
        utils.waitElementToBeClickable(bingSearchMobileBox);
        bingSearchMobileBox.sendKeys(word);
        bingSearchMobileBox.sendKeys(Keys.ENTER);
    }

}
