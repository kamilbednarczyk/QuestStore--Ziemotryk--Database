package services;

import views.AdminResponseCreator;

public class AdminService {
    private AdminResponseCreator adminResponseCreator = new AdminResponseCreator();

    public String renderIndexPage() {
        return adminResponseCreator.renderIndexPage();
    }
}
