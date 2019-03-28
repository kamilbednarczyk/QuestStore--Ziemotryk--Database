package controllers;

import com.sun.net.httpserver.HttpExchange;
import services.AdminService;
import sessionData.SessionHandler;

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

    public String getEditMentorPage(HttpExchange httpExchange) {
        return adminService.getEditMentorPageRender(httpExchange);
    }

    public String getAddMentorPage() {
        return adminService.getAddMentorPageRender();
    }

    public String getEditLevelPage(HttpExchange httpExchange) {
        return adminService.getEditLevelPageRender(httpExchange);
    }

    public String getAddLevelPage() {
        return adminService.getAddLevelPageRender();
    }

    public String getEditClassPage(HttpExchange httpExchange) {
        return adminService.getEditClassPageRender(httpExchange);
    }

    public String getAddClassPage() {
        return adminService.getAddClassPageRender();
    }

    public String getAdminResponse(HttpExchange httpExchange, String userPageRequest) {
        String response = "";
        if (userPageRequest.equals("index")) {
            response = getIndexPage();
        } else if (userPageRequest.equals("mentors")) {
            response = getMentorPage();
        } else if (userPageRequest.equals("classes")) {
            response = getClassPage();
        } else if (userPageRequest.equals("levels")) {
            response = getLevelPage();
        } else if (userPageRequest.equals("logout")) {
            new SessionHandler().removeActiveSessionWithCookie(httpExchange);
            response = new LoginController().getLoginPage();
        } else if (userPageRequest.equals("editMentorPage")) {
            response = getEditMentorPage(httpExchange);
        } else if (userPageRequest.equals("addMentorPage")) {
            response = getAddMentorPage();
        } else if (userPageRequest.equals("editLevelPage")) {
            response = getEditLevelPage(httpExchange);
        } else if (userPageRequest.equals("addLevelPage")) {
            response = getAddLevelPage();
        } else if (userPageRequest.equals("editClassPage")) {
            response = getEditClassPage(httpExchange);
        } else if (userPageRequest.equals("addClassPage")) {
            response = getAddClassPage();
        } else {
            response = "error404";
        }

        return response;
    }
}
