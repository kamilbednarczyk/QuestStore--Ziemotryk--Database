package sessionData;

import models.Account;

import com.sun.net.httpserver.HttpExchange;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class SessionHandler {
    private CookieHandler cookieHandler = new CookieHandler();

    private Map<String, String[]> activeSessionList;

    public SessionHandler() {
        activeSessionList = new HashMap<>();
    }

    public Map<String, String[]> getActiveSessionList() {
        return activeSessionList;
    }

    public void addSession(Account account, Optional<HttpCookie> cookie, HttpExchange httpExchange) {
        String randomSessionId = getRandomSessionId();
        String accountId = String.valueOf(account.getAccountId());
        String permission = String.valueOf(account.getPermission());

        String[] accountData = {accountId, permission};

        activeSessionList.put(randomSessionId, accountData);
        cookieHandler.addSessionIdCookie(randomSessionId, httpExchange);
    }

    private String getRandomSessionId() {
        String randomId = "";
        Random random = new Random();

        int currentRandomNumber;
        do {
            for(int i=0; i<10; i++) {
                currentRandomNumber =
                        random.ints(33, 123)
                                .findFirst().getAsInt();
                randomId += (char) currentRandomNumber;
            }
        } while(activeSessionList.containsKey(randomId));

        return randomId;
    }


}
