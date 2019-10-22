package framework.browsers;

import framework.logger.MyLogger;
import framework.utils.PropertiesManager;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;

public class BrowserFactory {
    private static WebDriver webDriver;
    private static MyLogger LOGGER = new MyLogger();

    public static WebDriver chooseBrowser() {
        switch (PropertiesManager.getProperties("browser")) {
            case ("Chrome"):
                WebDriverManager.getInstance(CHROME).setup();
                webDriver = new ChromeDriver(chromeOptions());
                LOGGER.info("browser is CHROME");
                break;
            case ("Firefox"):
                WebDriverManager.getInstance(FIREFOX).setup();
                webDriver = new FirefoxDriver(fireFoxOptions());
                LOGGER.info("browser is FIREFOX");
                break;
            default:
                LOGGER.info("driver should be Firefox or Chrome");
                throw new IllegalArgumentException("driver should be Firefox or Chrome");
        }
        return webDriver;
    }


    public static ChromeOptions chromeOptions() {
        String pathForDownload = PropertiesManager.getProperties("path");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("intl.accept_languages", PropertiesManager.getProperties("locale"));
        chromePrefs.put("download.default_directory", System.getProperty("user.dir") + "/" + pathForDownload);
        chromePrefs.put("safebrowsing.enabled", "false");
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<>();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--disable-extensions");
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriverManager.chromedriver().setup();
        return options;
    }

    public static FirefoxOptions fireFoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        String pathToDownload = PropertiesManager.getProperties("path");
        options.addPreference("intl.accept_languages", PropertiesManager.getProperties("locale"));
        options.addPreference("browser.download.dir", System.getProperty("user.dir") + "/" + pathToDownload);
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream, application/x-debian-package");
        WebDriverManager.firefoxdriver().setup();
        return options;
    }
}
