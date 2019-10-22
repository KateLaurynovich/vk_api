package application.pageObject;

import framework.elements.Label;
import framework.logger.MyLogger;
import org.openqa.selenium.By;


public class FeedPage extends BasePageVK{
    private static final MyLogger LOGGER = new MyLogger();
    private static final String filters = "feed_filters";

    public boolean isPostField() {
        LOGGER.info("feed page open");
        return new Label(By.id(filters), "feed_filters").isDisplayed();
    }
}
