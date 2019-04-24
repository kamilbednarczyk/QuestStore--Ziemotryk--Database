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
        AccountsDAO accountsDAO = new AccountsDAO();
        MentorsDAO mentorsDAO = new MentorsDAO();

        Account newAccount = new Account("test", "test", 2);
        accountsDAO.add(newAccount);
        Account newAccountFromDb = accountsDAO.getAccountFromDbByAccountWithoutId(newAccount);
        // acc id = 12
        Mentor newMentor = new Mentor(newAccountFromDb.getAccountId(), "bomba konga", "zubizubi@bubi.pl", 12, "funny momby", "emptyAvatar404.jpg");
        mentorsDAO.add(newMentor);
    }
}
