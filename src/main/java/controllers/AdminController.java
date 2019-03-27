package controllers;

import services.AdminService;

public class AdminController {
    private AdminService adminService = new AdminService();

    public String getIndexPageRender() {
        return adminService.renderIndexPage();
    }
}
