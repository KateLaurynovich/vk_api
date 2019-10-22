package application.forms;

import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

public class Header {

    private static final String header = "page_header_cont";
    private static final String logo = "top_home_logo";
    private static final String profile = "top_profile_link";

    public TopProfile getTopProfile() {
        new Button(By.id(profile), "profile").clickElement();
        return new TopProfile();
    }

    public Label getLogo() {
        return new Label(By.className(logo), "logo");
    }


}
