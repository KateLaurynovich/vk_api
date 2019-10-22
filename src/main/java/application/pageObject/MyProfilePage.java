package application.pageObject;


import application.forms.Post;

import framework.elements.Label;
import org.openqa.selenium.By;

public class MyProfilePage extends BasePageVK {
    private static final String myInfo = "page_info_wrap";

    public boolean isMyProfilePage() {
        return new Label(By.id(myInfo), "page_info_wrap").isDisplayed();
    }

    public Post getPost() {
        return new Post();
    }

}
