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

    <E> String renderPageWith(String fileName, List<E> listToFillWith) {
        String response;
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/" + fileName);
        JtwigModel model;
        model = JtwigModel.newModel().with("list", listToFillWith);
        response = template.render(model);
        return response;
    }

    String renderPage(String fileName) {
        return renderPageWith(fileName, new ArrayList<>());
    }

    <E, F> String renderPageWith(String fileName, List<E> firstList, List<F> secondList) {
        String response;
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/" + fileName);
        JtwigModel model;
        model = JtwigModel.newModel().with("list", firstList);
        model.with("list2", secondList);
        response = template.render(model);
        return response;
    }
}
