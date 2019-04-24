package views;

import models.Mentor;

import java.util.List;

public class MentorResponseCreator {
    private ResponseCreator responseCreator;

    public MentorResponseCreator() {
        this.responseCreator = new ResponseCreator();
    }

    public String renderIndexPage(List<Mentor> mentor) {
        return responseCreator.renderPageWith("/mentor/indexPage.twig", mentor);
    }
}
