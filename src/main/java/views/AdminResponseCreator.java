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

    public String renderEditMentorPage(List<Mentor> mentor, List<Class> possibleClasses) {
        return responseCreator.renderPageWith("/admin/editMentorPage.twig", mentor, possibleClasses);
    }

    public String renderAddMentorPage(List<Class> possibleClasses) {
        return responseCreator.renderPageWith("/admin/addMentorPage.twig", possibleClasses);
    }

    public String renderEditLevelPage(List<Level> level) {
        return responseCreator.renderPageWith("/admin/editLevelPage.twig", level);
    }

    public String renderAddLevelPage() {
        return responseCreator.renderPage("/admin/addLevelPage.twig");
    }

    public String renderEditClassPage(List<Class> classes) {
        return responseCreator.renderPageWith("/admin/editClassPage.twig", classes);
    }

    public String renderAddClassPage() {
        return responseCreator.renderPage("/admin/addClassPage.twig");
    }
}
