package application.forms;

import framework.elements.Form;
import org.openqa.selenium.By;

public class AuthorizationForm {

    private static String form = "//div[@id = 'quick_login']";

    public Form getForm() {
        return new Form(By.xpath(form), "AuthorizationForm");
    }

}
