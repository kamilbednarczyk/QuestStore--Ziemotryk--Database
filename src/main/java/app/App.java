package app;

import com.sun.net.httpserver.HttpServer;
import router.Router;
import staticHandler.Static;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {

    public void run() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8100), 0);

        server.createContext("/quest", new Router());
        server.createContext("/", new Static());


        server.setExecutor(null);

        System.out.println("Server starting...");
        server.start();
    }
}
