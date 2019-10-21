package utils;

import com.relevantcodes.extentreports.LogStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer {

    private static final Logger log = LogManager.getLogger(RetryTest.class);
    private int retryCount = 0;
    private int maxRetryCount = 2;

    public boolean retry(ITestResult result) {
        StringBuilder message = new StringBuilder("Retrying test ");

        if (retryCount < maxRetryCount) {
            result.setStatus(ITestResult.FAILURE);
            message.append(result.getName()).append(" with status ").append(getResultStatusName(result.getStatus())).append(" for the ").append(retryCount + 1).append(" time(s)");
            log.error(message.toString());

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
