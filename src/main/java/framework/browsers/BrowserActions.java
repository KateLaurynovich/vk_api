package framework.browsers;

import framework.logger.MyLogger;
import framework.utils.PropertiesManager;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BrowserActions {

    private static MyLogger LOGGER = new MyLogger();
    private static WebDriver webDriver;

    public BrowserActions() {
    }

    public static WebDriver getBrowser() {
        if (webDriver == null) {
            webDriver = BrowserFactory.chooseBrowser();
        }
        return webDriver;
    }

    public static void openPage() {
        getBrowser().manage().window().maximize();
        getBrowser().get(PropertiesManager.getProperties("URL"));
        LOGGER.info("start page is opened");
        int timeout = Integer.parseInt(PropertiesManager.getProperties("timeout"));
        getBrowser().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        getBrowser().manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
        getBrowser().manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS);
    }

    public static void goToPage(String url) {
        getBrowser().get(url);
        getBrowser().navigate().refresh();
    }

    public static void backBrowser() {
        getBrowser().navigate().back();
    }

    public static void quitBrowser() {
        getBrowser().quit();
        webDriver = null;
    }

    public static String getUrl() {
        return webDriver.getCurrentUrl();
    }
}
