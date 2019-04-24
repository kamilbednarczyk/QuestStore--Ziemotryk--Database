package services;

import databaseAccess.MentorsDAO;
import models.Mentor;
import sessionData.CookieHandler;
import sessionData.SessionHandler;
import views.MentorResponseCreator;

import java.util.ArrayList;
import java.util.List;

public class MentorService {
    private MentorResponseCreator mentorResponseCreator = new MentorResponseCreator();
    private CookieHandler cookieHandler = new CookieHandler();
    private SessionHandler sessionHandler = SessionHandler.getInstance();

    public String getIndexPageRender(int id) {
        List<Mentor> mentor = new ArrayList<>();
        mentor.add(new MentorsDAO().get(id));

        return mentorResponseCreator.renderIndexPage(mentor);
    }
}
