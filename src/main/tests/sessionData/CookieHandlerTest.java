package sessionData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.HttpCookie;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CookieHandlerTest {
    private CookieHandler cookieHandler;

    @BeforeEach
    public void setup(){
        cookieHandler = new CookieHandler();
    }

    @Test
    public void getSessionIdCookieValue_changeCookieWithQuotes_returnClearSessionIdString(){
        HttpCookie httpCookie = mock(HttpCookie.class);
        Optional<HttpCookie> cookie = Optional.of(httpCookie);
        String cookieString = "\"a";
        when(cookie.get().getValue()).thenReturn(cookieString);

        String answer = cookieHandler.getSessionIdCookieValue(cookie);

        assertEquals("a", answer);

    }
}