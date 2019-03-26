package app;

import com.sun.net.httpserver.HttpServer;
import router.Router;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {

    public void run() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/", new Router());

        server.setExecutor(null);

        System.out.println("Server starting...");
        server.start();
    }
}
