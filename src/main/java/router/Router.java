package router;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controllers.AdminController;
import controllers.CodecoolerController;
import controllers.LoginController;
import controllers.MentorController;
import databaseAccess.AccountsDAO;
import models.Account;
import models.Codecooler;
import sessionData.SessionHandler;
import sessionData.CookieHandler;
import views.ResponseCreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.net.URLDecoder;
import java.util.*;

public class Router implements HttpHandler {
    private SessionHandler sessionHandler;
    private CookieHandler cookieHandler;
    private LoginController loginController;
    private CodecoolerController codecoolerController;
    private MentorController mentorController;
    private AdminController adminController;


    public Router() {
        this.sessionHandler = new SessionHandler();
        this.cookieHandler = new CookieHandler();
        this.loginController = new LoginController();
        this.codecoolerController = new CodecoolerController();
        this.mentorController = new MentorController();
        this.adminController = new AdminController();

        AccountsDAO accountsDAO = new AccountsDAO();
        Account account = new Account("patryk", "mandrak", 3);
        accountsDAO.add(account);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("Request...");
        Optional<HttpCookie> cookie = cookieHandler.getSessionIdCookie(httpExchange);
        String method = httpExchange.getRequestMethod();
        String response = "";

        if (!cookie.isPresent() && method.equals("GET")) {
            response = loginController.getLoginPage();
        } else if (!cookie.isPresent() && method.equals("POST")) {
            // check inputs
            Map<String, String> loginDataMap = getFormInputsMap(httpExchange);
            // validate inputs && send response
            Account account = loginController.logIn(loginDataMap);

            if (account == null || loginDataMap.isEmpty()) {
                response = loginController.getLoginPage();
            } else {
                sessionHandler.addSession(account, cookie, httpExchange);
                response = connectToControllerBy(account);
            }
        } else if (cookie.isPresent()) {
            if (method.equals("GET")) {

            } else if (method.equals("POST")){

            }
        }

        System.out.println("Response...");

        sendResponse(httpExchange, response, cookie);
    }

    private String connectToControllerBy(Account account) {
        int permission = account.getPermission();
        int accountId = account.getAccountId();

        switch (permission) {
            case 3:
                return adminController.getIndexPageRender();

        }
        return null;
    }

    private void sendResponse(HttpExchange httpExchange, String response, Optional<HttpCookie> cookie) throws IOException {
        cookieHandler.setResponseCookieIfPresent(httpExchange, cookie);
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private Map<String, String> getFormInputsMap(HttpExchange httpExchange) throws IOException {
        Map<String, String> map = new HashMap<>();
        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String formData = br.readLine();
        String[] pairs = formData.split("&");
        if (formData.length() > 16) {
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                String value = new URLDecoder().decode(keyValue[1], "UTF-8");
                map.put(keyValue[0], value);
            }
        }
        return map;
    }

}
