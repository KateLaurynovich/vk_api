package application.pageObject;

import framework.elements.Button;
import framework.elements.Input;
import framework.logger.MyLogger;
import org.openqa.selenium.By;

public class MainePage {
    private static final MyLogger LOGGER = new MyLogger();

    private static final String mailId = "index_email";
    private static final String passId = "index_pass";
    private static final String loginBtn = "index_login_button";

    public void submitEmail(String email) {
        Input inputEmail = new Input(By.id(mailId), "index_email");
        inputEmail.submitInput(email);
    }

    public void submitPassword(String password) {
        Input inputPassword = new Input(By.id(passId), "index_email");
        inputPassword.submitInput(password);
    }

    public void clickLoginBtn() {
        Button buttonLogin = new Button(By.id(loginBtn), "index_login_button");
        buttonLogin.clickElement();
    }

    public void authorization(String email, String password) {
        LOGGER.info("authorization");
        submitEmail(email);
        submitPassword(password);
        clickLoginBtn();
    }
}
