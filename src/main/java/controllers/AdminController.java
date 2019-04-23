package controllers;

import com.sun.net.httpserver.HttpExchange;
import services.AdminService;
import sessionData.SessionHandler;

import java.io.IOException;
import java.util.Map;
import java.util.function.Function;

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

//        Map<String, Function> map;
//        map.get("key").apply(httpExchange);


        switch (userPageRequest) {
            case "index":  // GET: index
                response = getIndexPage();

                break;
            case "mentors":  // GET: mentors
                response = getMentorPage();

                break;
            case "classes":  // GET: classes
                response = getClassPage();

                break;
            case "levels":  // GET: levels
                response = getLevelPage();

                break;
            case "logout":  // GET: logout
                SessionHandler.getInstance().removeActiveSessionWithCookie(httpExchange);
                response = new LoginController().getLoginPage();

                break;
            case "editMentorPage":  // GET: edit mentor
                response = getEditMentorPage(httpExchange);

                break;
            case "addMentorPage":  // GET: add mentor
                response = getAddMentorPage();

                break;
            case "editLevelPage":  // GET: edit level
                response = getEditLevelPage(httpExchange);

                break;
            case "addLevelPage":  // GET: add level
                response = getAddLevelPage();

                break;
            case "editClassPage":  // GET: edit class
                response = getEditClassPage(httpExchange);

                break;
            case "addClassPage":  // GET: add class
                response = getAddClassPage();

                break;
            case "addMentor":  // POST: add mentor
                adminService.addMentor(httpExchange);
                response = getMentorPage();

                break;
            case "updateMentor":  // POST: edit mentor
                System.out.println("IM IN CONTROLLER UPDATE MENTOR");
                adminService.updateMentor(httpExchange, requestedItemId);
                response = getMentorPage();

                break;
            case "deleteMentor":  // POST: delete mentor
                adminService.deleteMentor(requestedItemId);
                response = getMentorPage();

                break;
            case "addLevel":  // POST: add level
                adminService.addLevel(httpExchange);
                response = getLevelPage();

                break;
            case "updateLevel":  // POST: update level
                adminService.updateLevel(httpExchange, requestedItemId);
                response = getLevelPage();

                break;
            case "deleteLevel":  // POST: delete level
                adminService.deleteLevel(requestedItemId);
                response = getLevelPage();

                break;
            case "addClass":  // POST: add class
                adminService.addClass(httpExchange);
                response = getClassPage();

                break;
            case "updateClass":  // POST: update Class
                adminService.updateClass(httpExchange, requestedItemId);
                response = getClassPage();

                break;
            case "deleteClass":  // POST delete class
                adminService.deleteClass(httpExchange, requestedItemId);
                response = getClassPage();

                break;
            default:
                response = "Error 404 (Not found)";
                break;
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
