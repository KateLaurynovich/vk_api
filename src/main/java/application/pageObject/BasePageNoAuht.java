package application.pageObject;

import application.forms.HeaderNoAuth;

public class BasePageNoAuht {

    public HeaderNoAuth getHeader() {
        return new HeaderNoAuth();
    }

}
