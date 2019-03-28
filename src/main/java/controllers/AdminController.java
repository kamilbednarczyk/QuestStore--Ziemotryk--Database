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

    public String getAdminResponse(HttpExchange httpExchange, String userPageRequest) {
        String response = "";
        if(userPageRequest.equals("index")) {
            response = getIndexPage();
        } else if(userPageRequest.equals("mentors")) {
            response = getMentorPage();
        } else if(userPageRequest.equals("classes")) {
            response = getClassPage();
        } else if(userPageRequest.equals("levels")) {
            response = getLevelPage();
        } else if(userPageRequest.equals("logout")) {
            new SessionHandler().removeActiveSessionWithCookie(httpExchange);
            response = new LoginController().getLoginPage();
        } else {
            response = "Error 404";
        }

        return response;
    }
}
