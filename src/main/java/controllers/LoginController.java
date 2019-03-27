package controllers;

import services.LoginService;

public class LoginController {
    private LoginService loginService = new LoginService();

    public String getLoginPage() {
        return loginService.getLoginPageRender();
    }
}
