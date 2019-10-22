package application.pageObject;

import application.forms.Header;
import application.forms.SideBar;

public class BasePageVK {

    public Header getHeader() {
        return new Header();
    }

    public SideBar getSideBar() {
        return new SideBar();
    }
}
