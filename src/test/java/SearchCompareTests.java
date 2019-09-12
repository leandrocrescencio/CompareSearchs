import objects.SearchObjects;
import utils.Propertie;
import org.testng.annotations.Test;
import setup.Setup;
import utils.RetryTest;

public class SearchCompareTests extends Setup {

    @Test(groups = "regression", description = "Search on Google Desktop", retryAnalyzer = RetryTest.class)
    public void googleDesktopSearch() {
        SearchObjects home = new SearchObjects(getDriver());
        home.googleSearch(Propertie.getValue("phrase"));
    }

    @Test(groups = "regression", description = "Search on Google Mobile", retryAnalyzer = RetryTest.class)
    public void googleMobileSearch() {
        SearchObjects home = new SearchObjects(getDriver());
        home.googleSearch(Propertie.getValue("phrase"));
    }

    @Test(groups = "regression", description = "Search on Bing Desktop", retryAnalyzer = RetryTest.class)
    public void bingDesktopSearch() {
        SearchObjects home = new SearchObjects(getDriver());
        home.bingSearch(Propertie.getValue("phrase"));
    }

    @Test(groups = "regression", description = "Search on Bing Mobile", retryAnalyzer = RetryTest.class)
    public void bingMobileSearch() {
        SearchObjects home = new SearchObjects(getDriver());
        home.bingSearchMobile(Propertie.getValue("phrase"));
    }




}
