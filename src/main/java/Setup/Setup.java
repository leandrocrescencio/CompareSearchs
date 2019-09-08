package Setup;

import Utils.Static;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import Utils.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Setup {
    public static final Logger log = LoggerFactory.getLogger("P4P");
    public static WebDriver driver;
    public String currentWindowHandle;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite(alwaysRun = true)
    public void extentSetup(ITestContext context) {
        if (!context.getName().contains("Default") && !context.getName().contains("Surefire") && !context.getName().contains("Gradle")) {
            Static.setTestName(context.getCurrentXmlTest().getSuite().getName());
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeScenario(Method method) {
        Test test = method.getAnnotation(Test.class);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-plugins");
        options.addArguments("--no-sandbox");
        options.addArguments("--ignore-urlfetcher-cert-requests");
        options.addArguments("--disable-infobars");
        options.addArguments("--dns-prefetch-disable");

        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("device", "Nexus 7");
        deviceMetrics.put("width", 375);
        deviceMetrics.put("height", 812);
        deviceMetrics.put("pixelRatio", 3.0);
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 8.1.0; SM-J530G) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Mobile Safari/537.36");

        String[] chromium = {"Windows", "Mac", "Linux"};
        int i;
        for (i = 0; i < chromium.length; i++)
            if (Static.OS.contains(chromium[i])) break;

        switch (i) {
            case 0: //Windows
                if (test.description().contains("Mobile") || test.description().contains("mobile")) {
                    options.setCapability(Propertie.getValue("DRIVER_CAPABILITY"), Propertie.getValue("chromeWin"));
                    options.setExperimentalOption("mobileEmulation", mobileEmulation);
                }
                System.setProperty(Propertie.getValue("DRIVER"), Static.PATH_PROJECT + Propertie.getValue("driverWin"));
                break;
            case 1: //Mac
                if (test.description().contains("Mobile") || test.description().contains("mobile")) {
                    options.setCapability(Propertie.getValue("DRIVER_CAPABILITY"), Propertie.getValue("chromeMac"));
                    options.setExperimentalOption("mobileEmulation", mobileEmulation);
                }
                System.setProperty(Propertie.getValue("DRIVER"), Static.PATH_PROJECT + Propertie.getValue("driverMac"));
                break;
            case 2: //Linux
                if (test.description().contains("Mobile") || test.description().contains("mobile")) {
                    options.setCapability(Propertie.getValue("DRIVER_CAPABILITY"), Propertie.getValue("chromeLinux"));
                    options.setExperimentalOption("mobileEmulation", mobileEmulation);
                }
                System.setProperty(Propertie.getValue("DRIVER"), Static.PATH_PROJECT + Propertie.getValue("driverLinux"));

                break;
        }
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        if (test.description().contains("Mobile") || test.description().contains("mobile")) {
            manageWindowsPosition();
        } else {
            driver.manage().window().maximize();
        }
        String[] websearchs = {"Google", "Bing"};
        for (i = 0; i < websearchs.length; i++)
            if (test.description().contains(websearchs[i])) break;

        switch (i) {
            case 0: // GOOGLE
                driver.get(Propertie.getValue("url1"));
                break;
            case 1: // BING
                driver.get(Propertie.getValue("url2"));
                break;
        }

        currentWindowHandle = driver.getWindowHandle();

        log.info("Starting scenario: {}", test.description());

        if (Static.getTestName().contains("Default") && Static.getTestName().contains("Suite")) {
            Static.setTestName(this.getClass().getSimpleName());
        }
        Utils.waitForPageLoadComplete(driver);

        ExtentTestManager.startTest(method.getName(), test.description());
    }

    @AfterMethod(alwaysRun = true)
    protected void afterMethod(ITestResult result) {
        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group); // new
        }
        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed", ExtentTestManager.getTest().addBase64ScreenShot(ScreenShot.capture()));
                ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
                break;
            case ITestResult.SKIP:
                ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped", ExtentTestManager.getTest().addBase64ScreenShot(ScreenShot.capture()));
                ExtentTestManager.getTest().log(LogStatus.SKIP, getStackTrace(result.getThrowable()));
                break;
            case ITestResult.SUCCESS:
                ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
                break;
            default:
                if (ExtentTestManager.getTest().getRunStatus().equals(LogStatus.UNKNOWN)) {
                    ExtentTestManager.getTest().log(LogStatus.SKIP, "This test method is skipped");
                } else {
                    ExtentTestManager.endTest();
                }
        }
        ExtentManager.getReporter(Static.getTestName()).endTest(ExtentTestManager.getTest());
        ExtentManager.getReporter(Static.getTestName()).flush();

        driver.quit();
        log.info("Execution finished\n+++++++++++++++++++++++++++++++++++++");

    }

    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    private void manageWindowsPosition() {

        Dimension windowSize = driver.manage().window().getSize();
        int desiredHeight = windowSize.height;
        int desiredWidth1 = (int) (windowSize.width / 3.8);
        Dimension desiredSize = new Dimension(desiredWidth1, desiredHeight);
        driver.manage().window().setSize(desiredSize);
        driver.manage().window().setPosition(new Point(10, 0));
    }

}
