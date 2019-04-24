package controllers;

import models.Codecooler;
import services.CodecoolerService;

import com.sun.net.httpserver.HttpExchange;
import sessionData.SessionHandler;

public class CodecoolerController {
    private CodecoolerService codecoolerService = new CodecoolerService();

    public String getIndexPage(int accountId) {
        return codecoolerService.getIndexPageRender(accountId);
    }

    public String getIndexPage(HttpExchange httpExchange) {
        return codecoolerService.getIndexPageRender(httpExchange);
    }

    public String getArtifactStorePage() {
        return codecoolerService.getArtifactStorePageRender();
    }

    public String getBackpackPage(HttpExchange httpExchange) {
        return codecoolerService.getBackpackPageRender(httpExchange);
    }

    public String getCodecoolerResponse(HttpExchange httpExchange, String userPageRequest) {
        int requestedItemId = getItemIdRequestIfExists(httpExchange);
        String response = "";

        switch (userPageRequest) {
            case "index":
                response = getIndexPage(httpExchange);
                break;

            case "artifactStore":
                response = getArtifactStorePage();
                System.out.println("23");
                break;

            case "backpack":
                response = getBackpackPage(httpExchange);
                break;

            case "logout":
                SessionHandler.getInstance().removeActiveSessionWithCookie(httpExchange);
                response = new LoginController().getLoginPage();
                break;

            case "buy":
                codecoolerService.buyArtifact(httpExchange);
                response = getArtifactStorePage();
                break;
        }
        return response;
    }

    private int getItemIdRequestIfExists(HttpExchange httpExchange) {
        int id = -1;
        String[] requestUrlArray = httpExchange.getRequestURI().toString().split("/");
        if (requestUrlArray.length >= 5) {
            id = Integer.parseInt(requestUrlArray[4]);
        }
        return id;
    }
}
