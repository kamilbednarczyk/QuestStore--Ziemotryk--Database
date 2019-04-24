package views;

import models.Artifact;
import models.Codecooler;
import models.Mentor;
import models.Quest;

import java.util.List;

public class MentorResponseCreator {
    private ResponseCreator responseCreator;

    // Constructor
    public MentorResponseCreator() { this.responseCreator = new ResponseCreator(); }

    // Methods
    public String renderIndexPage(List<Mentor> mentor) {
        return this.responseCreator.renderPageWith("/mentor/indexPage.twig", mentor);
    }

    public String renderCodecoolerPage(List<Codecooler> codecoolers) {
        return this.responseCreator.renderPageWith("/mentor/codecoolerPage.twig", codecoolers);
    }

    public String renderArtifactPage(List<Artifact> artifacts) {
        return this.responseCreator.renderPageWith("templates/mentor/artifactPage.twig", artifacts);
    }

    public String renderQuestPage(List<Quest> quest) {
        return this.responseCreator.renderPageWith("/mentor/questPage.twig", quest);
    }

    public String renderEditCodecoolerPage(List<Codecooler> codecooler) {
        return this.responseCreator.renderPageWith("/mentor/editCodecoolerPage.twig", codecooler);
    }

    public String renderAddCodecoolerPage() {
        return this.responseCreator.renderPage("/mentor/addCodecoolerPage.twig");
    }

    public String renderEditArtifactPage(List<Artifact> artifact) {
        return this.responseCreator.renderPageWith("/mentor/editArtifactPage.twig", artifact);
    }

    public String renderAddArtifactPage() {
        return this.responseCreator.renderPage("/mentor/addArtifactPage.twig");
    }

    public String renderEditQuestPage(List<Quest> quest) {
        return this.responseCreator.renderPageWith("/mentor/editQuestPage.twig", quest);
    }

    public String renderAddQuestPage() {
        return this.responseCreator.renderPage("/mentor/addQuestPage.twig");
    }
}
