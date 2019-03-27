package router;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controllers.LoginController;
import databaseAccess.AccountsDAO;
import models.Account;
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


    public Router() {
        this.sessionHandler = new SessionHandler();
        this.cookieHandler = new CookieHandler();
        this.loginController = new LoginController();
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

        if(!cookie.isPresent() && method.equals("GET")) {
            response = loginController.getLoginPage();
        }
        else if(!cookie.isPresent() && method.equals("POST")) {
            // check inputs
            Map<String, String > loginDataMap = getFormInputsMap(httpExchange);

            System.out.println(loginDataMap.get("password"));
            System.out.println(loginDataMap.get("login"));
            // validate inputs && send response
            Account account = loginController.logIn(loginDataMap);

            if(account == null || loginDataMap.isEmpty()) {
                System.out.println("null account");
                response = loginController.getLoginPage();
            } else {
                sessionHandler.addSession(account, cookie, httpExchange);
                response = "sss";
                System.out.println(sessionHandler.getActiveSessionList());
            }

        }

        if(cookie.isPresent()) {
            response = "dziala";
        }

        System.out.println("Response...");
        sendResponse(httpExchange, response, cookie);
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
        System.out.println(formData);
        System.out.println(formData.length());
        if(formData.length() > 16) {
            for(String pair : pairs){
                String[] keyValue = pair.split("=");
                System.out.println("dupa");
                System.out.println(keyValue[1]);
                String value = new URLDecoder().decode(keyValue[1], "UTF-8");
                map.put(keyValue[0], value);
            }
        }
        return map;
    }

}
