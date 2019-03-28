package controllers;

import com.sun.net.httpserver.HttpExchange;
import services.AdminService;
import sessionData.SessionHandler;

import java.io.IOException;

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

    public String getAdminResponse(HttpExchange httpExchange, String userPageRequest) throws IOException {
        int requestedItemId = getItemIdRequestIfExists(httpExchange);
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

        } else if (userPageRequest.equals("addMentor")) { // mentor
            adminService.addMentor(httpExchange);
            response = getMentorPage();

        } else if (userPageRequest.equals("updateMentor")) {
            adminService.updateMentor(httpExchange, requestedItemId);
            response = getMentorPage();

        } else if (userPageRequest.equals("deleteMentor")) {
            adminService.deleteMentor(requestedItemId);
            response = getMentorPage();

        } else if (userPageRequest.equals("addLevel")) { // level
            adminService.addLevel(httpExchange);
            response = getLevelPage();

        } else if (userPageRequest.equals("updateLevel")) {
            adminService.updateLevel(httpExchange, requestedItemId);
            response = getLevelPage();

        } else if (userPageRequest.equals("deleteLevel")) {
            adminService.deleteLevel(requestedItemId);
            response = getLevelPage();

        } else if (userPageRequest.equals("addClass")) { // class
            adminService.addClass(httpExchange);
            response = getClassPage();

        } else if (userPageRequest.equals("updateClass")) {
            adminService.updateClass(httpExchange, requestedItemId);
            response = getClassPage();

        } else if (userPageRequest.equals("deleteClass")) {
            adminService.deleteClass(httpExchange, requestedItemId);
            response = getClassPage();

        } else {
            response = "Error 404 (Not found)";
        }

        return response;
    }

    private int getItemIdRequestIfExists(HttpExchange httpExchange) {
        int id = -1;
        String[] requestUrlArray = httpExchange.getRequestURI().toString().split("/");
        if(requestUrlArray.length >= 5) {
            id = Integer.parseInt(requestUrlArray[6]);
        }
        return id;
    }
}
