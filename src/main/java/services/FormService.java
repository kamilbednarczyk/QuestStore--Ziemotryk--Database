package services;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class FormService {
    public static Map<String, String> getInputsStringMap(HttpExchange httpExchange) throws IOException {
        Map<String, String> map = new HashMap<>();
        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String formData = br.readLine();
        System.out.println(formData);
        String[] pairs = formData.split("&");
        if (formData.length() > 16) {
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                String value = new URLDecoder().decode(keyValue[1], "UTF-8");
                map.put(keyValue[0], value);
            }
        }
        return map;
    }
}
