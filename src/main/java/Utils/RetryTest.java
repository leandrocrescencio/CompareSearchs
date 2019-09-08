package Utils;

import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer {

    Logger log = LoggerFactory.getLogger("test");
    private int retryCount = 0;
    private int maxRetryCount = 2;

    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            result.setStatus(ITestResult.FAILURE);
            log.error("Retrying test " + result.getName()
                    + " with status " + getResultStatusName(result.getStatus())
                    + " for the " + (retryCount + 1) + " time(s)");

            extendReportsFailOperations();
            retryCount++;
            return true;
        }
        return false;
    }

    public String getResultStatusName(int status) {
        String resultName = null;
        if (status == 1)
            resultName = "SUCCESS";
        if (status == 2)
            resultName = "FAILURE";
        if (status == 3)
            resultName = "SKIP";
        return resultName;
    }

    public void extendReportsFailOperations() {
        ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed", ExtentTestManager.getTest().addBase64ScreenShot(ScreenShot.capture()));
    }
}
