package framework.elements;

import framework.browsers.BrowserActions;
import framework.logger.MyLogger;
import framework.wait.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class BaseForm {

    private static MyLogger LOGGER = new MyLogger();

    private String name;
    private By by;
    private WebElement webElement;

    BaseForm(By by, String name) {
        this.name = name;
        this.by = by;
        LOGGER.info("Create element ", name);
        webElement = BrowserActions.getBrowser().findElement(by);
        Waiter.waitElementVisible(by);
    }

    public boolean isDisplayed() {
        LOGGER.info(name + " - element is displayed");
        return webElement.isDisplayed();
    }
}
