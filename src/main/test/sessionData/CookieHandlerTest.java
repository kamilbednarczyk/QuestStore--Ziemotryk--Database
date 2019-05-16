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
    public void getSessionIdCookieValue_changeCookieWithQuotes_returnStringWithoutQuotes(){
        HttpCookie httpCookie = mock(HttpCookie.class);
        Optional<HttpCookie> cookie = Optional.of(httpCookie);
        String cookieString = "\"a\"b:b";


        when(cookie.get().getValue()).thenReturn(cookieString);
        String answer = cookieHandler.getSessionIdCookieValue(cookie);
        assertEquals("ab:b", answer);

    }

    @Test
    public void getSessionIdCookieValue_dontChangeAnythingWhenNoQuotes_returnOriginalString(){
        HttpCookie httpCookie = mock(HttpCookie.class);
        Optional<HttpCookie> cookie = Optional.of(httpCookie);
        String cookieString = "dupa";

        when(cookie.get().getValue()).thenReturn(cookieString);
        String answer = cookieHandler.getSessionIdCookieValue(cookie);
        assertEquals("dupa", answer);
    }
}