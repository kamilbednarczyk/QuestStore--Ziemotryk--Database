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

        if (userPageRequest.equals("index")) { // GET: index
            response = getIndexPage();

        } else if (userPageRequest.equals("mentors")) { // GET: mentors
            response = getMentorPage();

        } else if (userPageRequest.equals("classes")) { // GET: classes
            response = getClassPage();

        } else if (userPageRequest.equals("levels")) { // GET: levels
            response = getLevelPage();

        } else if (userPageRequest.equals("logout")) { // GET: logout
            new SessionHandler().removeActiveSessionWithCookie(httpExchange);
            response = new LoginController().getLoginPage();

        } else if (userPageRequest.equals("editMentorPage")) { // GET: edit mentor
            response = getEditMentorPage(httpExchange);

        } else if (userPageRequest.equals("addMentorPage")) { // GET: add mentor
            response = getAddMentorPage();

        } else if (userPageRequest.equals("editLevelPage")) { // GET: edit level
            response = getEditLevelPage(httpExchange);

        } else if (userPageRequest.equals("addLevelPage")) { // GET: add level
            response = getAddLevelPage();

        } else if (userPageRequest.equals("editClassPage")) { // GET: edit class
            response = getEditClassPage(httpExchange);

        } else if (userPageRequest.equals("addClassPage")) { // GET: add class
            response = getAddClassPage();

        } else if (userPageRequest.equals("addMentor")) { // POST: add mentor
            adminService.addMentor(httpExchange);
            response = getMentorPage();

        } else if (userPageRequest.equals("updateMentor")) { // POST: edit mentor
            System.out.println("IM IN CONTROLLER UPDATE MENTOR");
            adminService.updateMentor(httpExchange, requestedItemId);
            response = getMentorPage();

        } else if (userPageRequest.equals("deleteMentor")) { // POST: delete mentor
            adminService.deleteMentor(requestedItemId);
            response = getMentorPage();

        } else if (userPageRequest.equals("addLevel")) { // POST: add level
            adminService.addLevel(httpExchange);
            response = getLevelPage();

        } else if (userPageRequest.equals("updateLevel")) { // POST: update level
            adminService.updateLevel(httpExchange, requestedItemId);
            response = getLevelPage();

        } else if (userPageRequest.equals("deleteLevel")) { // POST: delete level
            adminService.deleteLevel(requestedItemId);
            response = getLevelPage();

        } else if (userPageRequest.equals("addClass")) { // POST: add class
            adminService.addClass(httpExchange);
            response = getClassPage();

        } else if (userPageRequest.equals("updateClass")) { // POST: update Class
            adminService.updateClass(httpExchange, requestedItemId);
            response = getClassPage();

        } else if (userPageRequest.equals("deleteClass")) { // POST delete class
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
        System.out.println("3");
        if(requestUrlArray.length >= 5) {
            System.out.println(requestUrlArray[4]);
            id = Integer.parseInt(requestUrlArray[4]);
        }
        return id;
    }
}
