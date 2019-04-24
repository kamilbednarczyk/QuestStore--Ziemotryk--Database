package services;

import com.sun.net.httpserver.HttpExchange;

public class UrlIdService {

    public static int getIdByUrl(HttpExchange httpExchange, int index) {
        return Integer
                .parseInt(httpExchange
                        .getRequestURI()
                        .toString().split("/")[index]);
    }
}
