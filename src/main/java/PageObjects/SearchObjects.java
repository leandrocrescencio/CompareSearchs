package PageObjects;

import PageElements.SearchElements;
import Utils.Utils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchObjects extends SearchElements {

    public static final Logger log = LoggerFactory.getLogger("test");
    private WebDriver driver;
    private Utils Utils;

    public SearchObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        Utils = new Utils(driver);
    }

    public void googleSearch(String word) {
            Utils.waitForPageLoadComplete(driver);
            Utils.waitElementToBeClickable(googleSearchBox);
            googleSearchBox.sendKeys(word);
            googleSearchBox.sendKeys(Keys.ENTER);
    }

    public void bingSearch(String word) {
        Utils.waitForPageLoadComplete(driver);
        Utils.waitElementToBeClickable(bingSearchBox);
        bingSearchBox.sendKeys(word);
        bingSearchBox.sendKeys(Keys.ENTER);
    }

    public void bingSearchMobile(String word) {
        Utils.waitForPageLoadComplete(driver);
        Utils.waitElementToBeClickable(bingSearchMobileBox);
        bingSearchMobileBox.sendKeys(word);
        bingSearchMobileBox.sendKeys(Keys.ENTER);
    }

}
