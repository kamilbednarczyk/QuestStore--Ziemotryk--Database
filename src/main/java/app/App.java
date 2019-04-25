package app;

import com.sun.net.httpserver.HttpServer;
import databaseAccess.*;
import models.*;
import router.Router;
import staticHandler.Static;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

public class App {

    public void run() throws IOException {
        //test();
        HttpServer server = HttpServer.create(new InetSocketAddress(7900), 0);

        server.createContext("/quest", new Router());
        server.createContext("/", new Static());


        server.setExecutor(null);

        System.out.println("Server starting...");
        server.start();
    }

    public void test() {
    }
}
