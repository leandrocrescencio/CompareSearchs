package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Setup.Setup;

public class ScreenShot {

    public static final Logger log = LoggerFactory.getLogger("P4P");
    private static String base64Encoded = "";

    private ScreenShot() {
        throw new IllegalStateException("Utility class");
    }

    public static String capture() {
        WebDriver webDriver = Setup.getDriver();
        base64Encoded = "data:image/png;base64," + ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
        return base64Encoded;
    }

}