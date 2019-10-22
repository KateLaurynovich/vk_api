package application.forms;

import framework.elements.Label;
import org.openqa.selenium.By;

public class HeaderNoAuth {
    private static final String logo = "top_home_logo";

    public Label getLogo() {
        return new Label(By.className(logo), "logo");
    }
}
