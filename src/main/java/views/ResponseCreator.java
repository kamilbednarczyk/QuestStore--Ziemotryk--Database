package views;

import models.Class;
import models.Mentor;
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

    public <E> String renderPageWith(String fileName, List<E> listToFillWith) {
        String response;
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/"+fileName);
        JtwigModel model;
        model = JtwigModel.newModel().with("list", listToFillWith);
        response = template.render(model);
        return response;
    }

    public String renderPage(String fileName) {
        return renderPageWith(fileName, new ArrayList<>());
    }

    public <E> String renderPageWith(String fileName, List<Mentor> firstList, List<Class> secondList) {
        String response;
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/"+fileName);
        JtwigModel model;
        model = JtwigModel.newModel().with("list", firstList);
        model.with("secondlist", secondList);
        response = template.render(model);
        return response;
    }
}
