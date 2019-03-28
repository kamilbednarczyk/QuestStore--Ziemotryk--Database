package services;

import com.sun.net.httpserver.HttpExchange;
import databaseAccess.ClassesDAO;
import databaseAccess.LevelsDAO;
import databaseAccess.MentorsDAO;
import models.Class;
import models.Level;
import models.Mentor;
import views.AdminResponseCreator;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private AdminResponseCreator adminResponseCreator = new AdminResponseCreator();

    public String getIndexPageRender() {
        return adminResponseCreator.renderIndexPage();
    }

    public String getMentorPageRender() {
        List<Mentor> mentors = new MentorsDAO().getAll();
        return adminResponseCreator.renderMentorPage(mentors);
    }

    public String getClassPageRender() {
        List<Class> classes = new ClassesDAO().getAll();
        return adminResponseCreator.renderClassPage(classes);
    }

    public String getLevelPageRender() {
        List<Level> levels = new LevelsDAO().getAll();
        return adminResponseCreator.renderLevelPage(levels);
    }

    public String getEditMentorPageRender(HttpExchange httpExchange) {
        int id = getIdByURL(httpExchange);
        List<Mentor> mentor = new ArrayList<>();
        mentor.add(new MentorsDAO().get(id));

        return adminResponseCreator.renderEditMentorPage(mentor);
    }

    public String getAddMentorPageRender() {
        return adminResponseCreator.renderAddMentorPage();
    }

    public String getEditLevelPageRender(HttpExchange httpExchange) {
        int id = getIdByURL(httpExchange);
        List<Level> level = new ArrayList<>();
        level.add(new LevelsDAO().get(id));

        return adminResponseCreator.renderEditLevelPage(level);
    }

    public String getAddLevelPageRender() {
        return adminResponseCreator.renderAddLevelPage();
    }

    public String getEditClassPageRender(HttpExchange httpExchange) {
        int id = getIdByURL(httpExchange);
        List<Class> classes = new ArrayList<>();
        classes.add(new ClassesDAO().get(id));

        return adminResponseCreator.renderEditClassPage(classes);
    }

    public String getAddClassPageRender() {
        return adminResponseCreator.renderAddClassPage();
    }

    private int getIdByURL(HttpExchange httpExchange) {
        int requestedId = Integer
                            .parseInt(httpExchange
                            .getRequestURI()
                            .toString().split("/")[4]);

        return requestedId;
    }
}
