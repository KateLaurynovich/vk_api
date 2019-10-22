package framework.elements;

import framework.browsers.BrowserActions;
import framework.logger.MyLogger;
import framework.wait.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public abstract class BaseElement {
    private static MyLogger LOGGER = new MyLogger();

    protected String name;
    protected By by;
    protected WebElement webElement;

    BaseElement(By by, String name) {
        this.name = name;
        this.by = by;
        LOGGER.info("Create element ", name);
        webElement = BrowserActions.getBrowser().findElement(by);
        Waiter.waitElementVisible(by);
    }

    public WebElement webElement() {
        Waiter.waitElementVisible(by);
        return webElement;
    }

    public void clickElement() {
        LOGGER.info("Click on element ", name);
        Waiter.waitElementClickable(webElement);
        webElement.click();
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return webElement.getText();
    }

    public String getAttribute(String attribute) {
        return webElement.getAttribute(attribute);
    }

    public boolean isDisplayed() {
        LOGGER.info(name +  " - is displayed");
        return webElement.isDisplayed();
    }

    public static boolean isDisplayed(By by) {
        return (BrowserActions.getBrowser().findElements(by).size() > 0);
    }

    public void moveToElement(){
        Actions action = new Actions(BrowserActions.getBrowser());
        Waiter.waitElementClickable(webElement);
        LOGGER.info("move to element"+ name);
        action.moveToElement(webElement);

    }
}
