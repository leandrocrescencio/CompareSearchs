package PageElements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchElements {

    //@FindBy(xpath = "//input[contains(@title,'Search')]")
    @FindBy(xpath = "//input[contains(@name,'q')]")
    protected WebElement googleSearchBox;

    @FindBy(xpath = "//input[contains(@name,'q')]")
    protected WebElement googleSearchMobileBox;


    @FindBy(xpath = "//input[contains(@class,'b_searchbox')]")
    protected WebElement bingSearchBox;

    @FindBy(xpath = "//input[contains(@id,'sb_form_q')]")
    protected WebElement bingSearchMobileBox;








}
