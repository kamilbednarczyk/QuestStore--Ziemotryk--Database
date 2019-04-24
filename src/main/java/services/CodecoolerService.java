package services;

import com.sun.net.httpserver.HttpExchange;
import databaseAccess.ArtifactsDAO;
import databaseAccess.BackpacksDAO;
import databaseAccess.CodecoolersDAO;
import models.Artifact;
import models.Backpack;
import models.Codecooler;
import sessionData.CookieHandler;
import sessionData.SessionHandler;
import views.CodecoolerResponseCreator;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CodecoolerService {
    private CodecoolerResponseCreator codecoolerResponseCreator = new CodecoolerResponseCreator();
    private CookieHandler cookieHandler = new CookieHandler();
    private SessionHandler sessionHandler = SessionHandler.getInstance();

    public String getIndexPageRender(int id) {

        List<Codecooler> codecooler = new ArrayList<>();
        codecooler.add(new CodecoolersDAO().get(id));

        return codecoolerResponseCreator.renderIndexPage(codecooler);
    }

    public String getIndexPageRender(HttpExchange httpExchange) {
        int id = getAccountIdBy(httpExchange);

        return getIndexPageRender(id);
    }

    public String getArtifactStorePageRender() {
        List<Artifact> artifacts = new ArtifactsDAO().getAll();

        return codecoolerResponseCreator.renderArtifactStorePage(artifacts);
    }

    public String getBackpackPageRender(HttpExchange httpExchange) {
        int id = getAccountIdBy(httpExchange);

        List<Backpack> backpacks = new CodecoolersDAO().get(id).getBackpacks();

        return codecoolerResponseCreator.renderBackpackPage(backpacks);
    }


    public boolean buyArtifact(HttpExchange httpExchange) {
        BackpacksDAO backpacksDAO = new BackpacksDAO();
        int artifactId = getIdByURL(httpExchange);
        int accountId = getAccountIdBy(httpExchange);
        Codecooler codecooler = new CodecoolersDAO().get(accountId);

        int amountOfCoolcoins = codecooler.getCoolcoins();
        int artifactPrize = new ArtifactsDAO().get(artifactId).getPrize();



        if (enoughCoins(amountOfCoolcoins, artifactPrize)) {
            codecooler.setCoolcoins(amountOfCoolcoins - artifactPrize);
            CodecoolersDAO codecoolersDAO = new CodecoolersDAO();
            codecoolersDAO.update(codecooler.getClassId(), codecooler);

            Backpack backpack = new Backpack(codecooler.getBackpackId(), artifactId, false);
            backpacksDAO.add(backpack);

            return true;
        } else {
            return false;
        }

    }

    private boolean enoughCoins(int codecoolerCoolcoins, int artifactPrize) {
        if (codecoolerCoolcoins >= artifactPrize) {
            return true;
        } else {
            return false;
        }
    }

    private int getAccountIdBy(HttpExchange httpExchange) {
        Optional<HttpCookie> cookie = cookieHandler.getSessionIdCookie(httpExchange);
        return sessionHandler.getAccountIdFromCookie(cookie);
    }

    private int getIdByURL(HttpExchange httpExchange) {
        return Integer
                .parseInt(httpExchange
                        .getRequestURI()
                        .toString().split("/")[4]);
    }
}
