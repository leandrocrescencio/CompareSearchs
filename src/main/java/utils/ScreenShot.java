package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import setup.Setup;

public class ScreenShot {

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