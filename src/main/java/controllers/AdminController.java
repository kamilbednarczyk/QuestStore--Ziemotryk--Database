package controllers;

import services.AdminService;

public class AdminController {
    private AdminService adminService = new AdminService();

    public String getIndexPage() {
        return adminService.getIndexPageRender();
    }
    
    public String getMentorPage() {
        return adminService.getMentorPageRender();
    }

    public String getClassPage() {
        return adminService.getClassPageRender();
    }

    public String getLevelPage() {
        return adminService.getLevelPageRender();
    }
}
