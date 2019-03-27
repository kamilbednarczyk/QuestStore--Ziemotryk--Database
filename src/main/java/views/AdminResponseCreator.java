package views;

import models.Class;
import models.Level;
import models.Mentor;

import java.util.List;

public class AdminResponseCreator {
    private ResponseCreator responseCreator;

    // Constructor
    public AdminResponseCreator() {
        this.responseCreator = new ResponseCreator();
    }

    // Methods
    public String renderIndexPage() {
        return responseCreator.renderPage("/admin/indexPage.twig");
    }

    public String renderMentorPage(List<Mentor> mentors) {
        return responseCreator.renderPageWith("/admin/mentorsPage.twig", mentors);
    }

    public String renderClassPage(List<Class> classes) {
        return responseCreator.renderPageWith("/admin/classesPage.twig", classes);
    }

    public String renderLevelPage(List<Level> levels) {
        return responseCreator.renderPageWith("/admin/levels.twig", levels);
    }
}
