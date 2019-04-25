package views;

import models.Artifact;
import models.Class;
import models.Codecooler;
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

//    public String renderPageWith(String fileName, Object... toModelWith) {
//        String response;
//        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/"+fileName);
//        JtwigModel model = JtwigModel.newModel();
//
//
//        model.with("list", toModelWith);
//        for(int i=1; i<toModelWith.length; i++) {
//            model.with("list"+(i+1), toModelWith[i]);
//            System.out.println("TUTAJ");
//            System.out.println("list"+i+1);
//        }
//        response = template.render(model);
//        return response;
//    }

    public <E> String renderPageWith(String fileName, List<E> listToFillWith) {
        if("templates/mentor/artifactPage.twig".equals(fileName)) System.out.println("IN"); // DELL LATER
        String response;
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/" + fileName);
        if("templates/mentor/artifactPage.twig".equals(fileName)) System.out.println("OUT 1"); // dell later
        JtwigModel model;
        model = JtwigModel.newModel().with("list", listToFillWith);
        if("templates/mentor/artifactPage.twig".equals(fileName)) System.out.println("OUT 2"); // dell later
        response = template.render(model);
        if("templates/mentor/artifactPage.twig".equals(fileName)) System.out.println("OUT 3"); // DELL LATER
        return response;
    }

    public String renderPage(String fileName) {
        return renderPageWith(fileName, new ArrayList<>());
    }

    public <E, F> String renderPageWith(String fileName, List<E> firstList, List<F> secondList) {
        String response;
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/" + fileName);
        JtwigModel model;
        model = JtwigModel.newModel().with("list", firstList);
        model.with("list2", secondList);
        response = template.render(model);
        return response;
    }

    private JtwigTemplate getTemplateByFile(String fileName) {
        return JtwigTemplate.classpathTemplate("templates/" + fileName);
    }

    private JtwigModel getNewModel() {
        return JtwigModel.newModel();
    }
//                      ======OLD=ONES======
//    public <E> String renderPageWith(String fileName, List<E> listToFillWith) {
//        String response;
//        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/"+fileName);
//        JtwigModel model;
//        model = JtwigModel.newModel().with("list", listToFillWith);
//        response = template.render(model);
//        return response;
//    }
//
//    public String renderPage(String fileName) {
//        return renderPageWith(fileName, new ArrayList<>());
//    }
//
//    public <E> String renderPageWith(String fileName, List<Mentor> firstList, List<Class> secondList) {
//        String response;
//        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/"+fileName);
//        JtwigModel model;
//        model = JtwigModel.newModel().with("list", firstList);
//        model.with("list2", secondList);
//        response = template.render(model);
//        return response;
//    }
}
