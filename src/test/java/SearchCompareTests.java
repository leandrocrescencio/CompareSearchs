import objects.SearchObjects;
import utils.Propertie;
import org.testng.annotations.Test;
import setup.Setup;
import utils.RetryTest;

public class SearchCompareTests extends Setup {

    @Test(groups = "regression", description = "Search on Google Desktop", retryAnalyzer = RetryTest.class)
    public void googleDesktopSearch() {
        SearchObjects home = new SearchObjects(driver);
        home.googleSearch(Propertie.getValue("phrase"));
    }

    @Test(groups = "regression", description = "Search on Google Mobile", retryAnalyzer = RetryTest.class)
    public void googleMobileSearch() {
        SearchObjects home = new SearchObjects(driver);
        home.googleSearch(Propertie.getValue("phrase"));
    }

    @Test(groups = "regression", description = "Search on Bing Desktop", retryAnalyzer = RetryTest.class)
    public void bingDesktopSearch() {
        SearchObjects home = new SearchObjects(driver);
        home.bingSearch(Propertie.getValue("phrase"));
    }

    @Test(groups = "regression", description = "Search on Bing Mobile", retryAnalyzer = RetryTest.class)
    public void bingMobileSearch() {
        SearchObjects home = new SearchObjects(driver);
        home.bingSearchMobile(Propertie.getValue("phrase"));
    }




}
