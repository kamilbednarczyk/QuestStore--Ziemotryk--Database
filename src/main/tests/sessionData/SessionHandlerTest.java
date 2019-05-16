package sessionData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.ws.spi.http.HttpExchange;
import java.net.HttpCookie;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SessionHandlerTest {

    private SessionHandler sessionHandler;

    @BeforeEach
    public void setup(){

        sessionHandler = SessionHandler.getInstance();
    }

    @Test
    public void getRandomSessionId_isCreateSessionId_returnSessionId(){

        assertTrue(sessionHandler.getRandomSessionId().length() != 0);

    }

    @Test
    public void getRandomSessionId_isSessionIdHas19signs_returnSessionId(){

        assertEquals(19, sessionHandler.getRandomSessionId().length());
    }




}