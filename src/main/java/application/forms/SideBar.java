package application.forms;

import application.enums.MenuCategory;
import framework.elements.Label;
import framework.logger.MyLogger;
import framework.utils.RegEx;
import org.openqa.selenium.By;

public class SideBar {
    private static final MyLogger LOGGER = new MyLogger();
    private static String elMenu = "//li[contains(@id, 'l_%s')]//a";

    public Label getElementMenu(MenuCategory name){
        return new Label(By.xpath(String.format(elMenu, name.getTitle())), name.getTitle());
    }

    public void clickElementMenu(MenuCategory name){
        getElementMenu(name).clickElement();
    }

    public Long getUserID() {
        Label linkID = getElementMenu(MenuCategory.PROFILE);
        Long userID = Long.parseLong(RegEx.findMatcher("\\d{1,10}", linkID.getAttribute("href")));
        LOGGER.info("userID = ", userID);
        return userID;
    }
}
