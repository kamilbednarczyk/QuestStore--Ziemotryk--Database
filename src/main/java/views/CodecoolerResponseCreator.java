package views;

import models.Artifact;
import models.Backpack;
import models.Codecooler;

import java.util.List;

public class CodecoolerResponseCreator {
    private ResponseCreator responseCreator;

    public CodecoolerResponseCreator() {
        this.responseCreator = new ResponseCreator();
    }


    public String renderIndexPage(List<Codecooler> currentCodecooler) {
        return responseCreator.renderPageWith("/codecooler/indexPage.twig", currentCodecooler);
    }

    public String renderArtifactStorePage(List<Artifact> artifacts) {
        return responseCreator.renderPageWith("/codecooler/artifactStore.twig", artifacts);
    }

    public String renderBackpackPage(List<Backpack> codecoolerBackpacks) {
        return responseCreator.renderPageWith("/codecooler/backpack.twig", codecoolerBackpacks);
    }
}
