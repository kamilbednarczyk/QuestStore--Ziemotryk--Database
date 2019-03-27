package services;

import views.ResponseCreator;

public class LoginService {
    private ResponseCreator responseCreator = new ResponseCreator();

    public String getLoginPageRender() {
        return responseCreator.renderLoginPage();
    }

    public void logIn(){
        //...
    }
}
