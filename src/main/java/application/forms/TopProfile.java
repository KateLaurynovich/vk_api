package application.forms;

import application.enums.TopCategory;
import framework.elements.Label;
import framework.logger.MyLogger;
import org.openqa.selenium.By;

public class TopProfile {

    private static final MyLogger LOGGER = new MyLogger();

    private static final String category = "top_%s_link";

    public Label getCategory(TopCategory topCategory) {
        return new Label(By.id(String.format(category, topCategory.getTitle())), topCategory.getTitle());
    }
}
