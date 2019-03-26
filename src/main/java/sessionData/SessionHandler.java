package sessionData;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SessionHandler {
    Map<String, String[]> activeSessionList;

    public SessionHandler() {
        activeSessionList = new HashMap<>();
    }

    public void addSession() {

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
