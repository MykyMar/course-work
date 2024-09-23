package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;


@Getter
public class DashboardPage {

    private final SelenideElement newProjectBtn = $("div.page-header a[href='/project/create']");
    private final ElementsCollection projectsList = $$("span.table-list-title a[href*='/board/']");

    //check project name in the projects list
    public boolean isProjectNameExist (String name) {
        open("/dashboard/1/projects");
        List<String> list = projectsList.texts();
        return list.contains(name);
    }

}
