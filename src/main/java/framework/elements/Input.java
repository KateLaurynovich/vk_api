package framework.elements;

import framework.logger.MyLogger;
import framework.wait.Waiter;
import org.openqa.selenium.By;

public class Input extends BaseElement {
    private static MyLogger LOGGER = new MyLogger();

    public Input(By by, String name) {
        super(by, name);
    }

    public void submitInput(String data) {
        Waiter.waitElementClickable(webElement);
        LOGGER.info("input data " + name);
        webElement.sendKeys(data);
    }
}
