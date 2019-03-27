package views;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.util.ArrayList;
import java.util.List;

public class ResponseCreator {

    public ResponseCreator() {

    }

    public String renderLoginPage() {
        String loginPage = renderPage("login.twig");
        return loginPage;
    }

    private String renderPageWith(String fileName, List<String> listToFillWith) {
        String response = "";
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/"+fileName);
        JtwigModel model;
        model = JtwigModel.newModel().with("list", listToFillWith);
        response = template.render(model);
        return response;
    }

    private String renderPage(String fileName) {
        return renderPageWith(fileName, new ArrayList<>());
    }
}
