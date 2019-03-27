package views;

public class AdminResponseCreator {
    private ResponseCreator responseCreator = new ResponseCreator();

    public String renderIndexPage() {
        return responseCreator.renderPage("/admin/indexPage.twig");
    }
}
