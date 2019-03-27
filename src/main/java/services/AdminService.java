package services;

import databaseAccess.ClassesDAO;
import databaseAccess.LevelsDAO;
import databaseAccess.MentorsDAO;
import models.Class;
import models.Level;
import models.Mentor;
import views.AdminResponseCreator;

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
}
