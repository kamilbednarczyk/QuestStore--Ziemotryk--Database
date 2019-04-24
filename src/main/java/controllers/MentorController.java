package controllers;

import services.MentorService;

public class MentorController {
    private MentorService mentorService = new MentorService();

    public String getIndexPage(int accountId) {
        return mentorService.getIndexPageRender(accountId);
    }
}
