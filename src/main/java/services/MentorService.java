package services;

import databaseAccess.*;
import models.Account;
import models.Artifact;
import models.Codecooler;
import models.Quest;
import views.MentorResponseCreator;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MentorService {
    private MentorResponseCreator mentorResponseCreator = new MentorResponseCreator();

    // GET METHODS
    public String getIndexPageRender() { return this.mentorResponseCreator.renderIndexPage(); }

    public String getCodecoolerPageRender() {
        List<Codecooler> codecoolers = new CodecoolersDAO().getAll();
        return this.mentorResponseCreator.renderCodecoolerPage(codecoolers);
    }

    public String getArtifactPageRender() {
        List<Artifact> artifacts = new ArtifactsDAO().getAll();
        return this.mentorResponseCreator.renderArtifactPage(artifacts);
    }

    public String getQuestPageRender() {
        List<Quest> quests = new QuestsDAO().getAll();
        return this.mentorResponseCreator.renderQuestPage(quests);
    }

    public String getEditCodecoolerPageRender(HttpExchange httpExchange) {
        int id = getIdByUrl(httpExchange);
        List<Codecooler> codecooler = new ArrayList<>();
        codecooler.add(new CodecoolersDAO().get(id));
        return this.mentorResponseCreator.renderEditCodecoolerPage(codecooler);
    }

    public String getAddCodecoolerPageRender() {
        return this.mentorResponseCreator.renderAddCodecoolerPage();
    }

    public String getEditArtifactPageRender(HttpExchange httpExchange) {
        int id = getIdByUrl(httpExchange);
        List<Artifact> artifact = new ArrayList<>();
        artifact.add(new ArtifactsDAO().get(id));
        return this.mentorResponseCreator.renderEditArtifactPage(artifact);
    }

    public String getAddArtifactPageRender() {
        return this.mentorResponseCreator.renderAddArtifactPage();
    }

    public String getEditQuestPageRender(HttpExchange httpExchange) {
        int id = getIdByUrl(httpExchange);
        List<Quest> quest = new ArrayList<>();
        quest.add(new QuestsDAO().get(id));

        return this.mentorResponseCreator.renderAddCodecoolerPage();
    }

    public String getAddQuestPageRender() {
        return this.mentorResponseCreator.renderAddQuestPage();
    }

    private int getIdByUrl(HttpExchange httpExchange) {
        return UrlIdService.getIdByUrl(httpExchange, 4);
    }

    // POST METHODS
    public void addCodecooler(HttpExchange httpExchange) throws IOException {
        Map<String, String> inputs = getFormInputsMap(httpExchange);

        Account account = getCodecoolerAccountFromForm(inputs);
        AccountsDAO accountsDAO = new AccountsDAO();
        accountsDAO.add(account);
        Account currentAccount = accountsDAO.getAccountFromDbByAccountWithoutId(account);

        Codecooler codecooler = getCodecoolerFromFormAndAccount(inputs, currentAccount);
        new CodecoolersDAO().add(codecooler);
    }

    public void updateCodecooler(HttpExchange httpExchange, int requestedItemId) throws IOException {
        Map<String, String> inputs = getFormInputsMap(httpExchange);

        Account account = new AccountsDAO().get(requestedItemId);
        Codecooler codecooler = getCodecoolerFromFormAndAccount(inputs, account);

        new CodecoolersDAO().update(requestedItemId, codecooler);
    }

    public void deleteCodecooler(int requestedItemId) {
        new AccountsDAO().delete(requestedItemId);
        new CodecoolersDAO().delete(requestedItemId);
    }

    public void addArtifact(HttpExchange httpExchange) throws IOException {
        Map<String, String> inputs = getFormInputsMap(httpExchange);
        Artifact artifactToAdd = getArtifactFromForm(inputs);
        new ArtifactsDAO().add(artifactToAdd);
    }

    public void updateArtficat(HttpExchange httpExchange, int requestedItemId) throws IOException {
        Map<String, String> inputs = getFormInputsMap(httpExchange);
        Artifact artifactToUpdate = getArtifactFromForm(inputs);
        new ArtifactsDAO().update(requestedItemId, artifactToUpdate);
    }

    public void deleteArtifact(int requestedItemId) {
        new ArtifactsDAO().delete(requestedItemId);
    }

    public void addQuest(HttpExchange httpExchange) throws IOException {
        Map<String, String> inputs = getFormInputsMap(httpExchange);
        Quest questToUpdate = getQuestFromForm(inputs);
        new QuestsDAO().add(questToUpdate);
    }

    public void updateQuest(HttpExchange httpExchange, int requestedItemId) throws IOException {
        Map<String, String> inputs = getFormInputsMap(httpExchange);
        Quest questToUpdate = getQuestFromForm(inputs);
        new QuestsDAO().update(requestedItemId, questToUpdate);
    }

    public void deleteQuest(int requestedItemId) {
        new QuestsDAO().delete(requestedItemId);
    }

    private Map<String, String> getFormInputsMap(HttpExchange httpExchange) throws IOException {
        return FormService.getInputsStringMap(httpExchange);
    }

    private Account getCodecoolerAccountFromForm(Map<String, String> inputs) {
        return new AccountFactory().getCodecoolerAccountFromForm(inputs);
    }

    private Codecooler getCodecoolerFromFormAndAccount(Map<String, String> inputs, Account account) {
        // ACCOUNT ID, CLASS ID, BACKPACK ID, FULL NAME, EMAIL, AVATARFILE, COOLCOINS
        return new Codecooler(
                account.getAccountId(),
                new ClassesDAO().getClassIdByName(String.valueOf(inputs.get("assignedClass"))),
                inputs.get("fullName"),
                inputs.get("email"),
                "emptyAvatar404.jpg" // none for now
        );
    }

    private Artifact getArtifactFromForm(Map<String, String> inputs) {
        return new Artifact(
                inputs.get("artifactName"),
                inputs.get("description"),
                Integer.parseInt(inputs.get("prize"))
        );
    }

    private Quest getQuestFromForm(Map<String, String> inputs) throws IOException {
        return new Quest(
            new CategoriesDAO().getCategoryIdByName(
                    inputs.get("categoryName")
            ), // ADD CATEGORY NAME INPUT TO TWIGS LATER
            inputs.get("questName"),
            inputs.get("description"),
            Integer.parseInt(inputs.get("questValue"))
        );
    }
}
