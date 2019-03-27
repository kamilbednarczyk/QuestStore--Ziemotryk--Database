package router;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controllers.LoginController;
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
    SessionHandler sessionHandler;
    CookieHandler cookieHandler;

    public Router() {
        this.sessionHandler = new SessionHandler();
        this.cookieHandler = new CookieHandler();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("Request...");
        Optional<HttpCookie> cookie = cookieHandler.getSessionIdCookie(httpExchange);
        String method = httpExchange.getRequestMethod();
        String response = "";
        if(!cookie.isPresent() && method.equals("GET")) {
            LoginController loginController = new LoginController();
            response = loginController.getLoginPage();
        }
        else if(!cookie.isPresent() && method.equals("POST")) {
            // check inputs
            Map<String, String > loginDataMap = getFormInputsMap(httpExchange);
            System.out.println(loginDataMap.get("password"));
            System.out.println(loginDataMap.get("login"));
            // validate inputs && send response
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
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }
}
