package framework.elements;

import framework.logger.MyLogger;
import framework.wait.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class Selector extends BaseElement {
    private static MyLogger LOGGER = new MyLogger();

    public Selector(By by, String name) {
        super(by, name);
    }

    private Select getSelector() {
        Waiter.waitElementClickable(webElement);
        return new Select(webElement);
    }

    public List<String> getCategory() {
        List<WebElement> names = getSelector().getOptions();
        List<String> select = new ArrayList<>();
        for (WebElement category : names) {
            select.add(category.getText());
        }
        return select;
    }

    public void chooseSelector(String date) {
        LOGGER.info(date + " - name select element");
        getSelector().selectByVisibleText(date);

    }
}
