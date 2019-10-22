package application.pageObject;

import application.forms.AuthorizationForm;
import application.forms.Post;

public class ProfilePageNoAuth extends BasePageNoAuht{

    public AuthorizationForm getAuthorizationForm(){
        return new AuthorizationForm();
    }

    public Post getPost() {
        return new Post();
    }

}
