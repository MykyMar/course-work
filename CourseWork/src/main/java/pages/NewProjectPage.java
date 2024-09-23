package pages;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

@Getter
public class NewProjectPage {
    private final SelenideElement formName = $(By.id("form-name"));
    private final SelenideElement formIdentifier = $(By.id("form-identifier"));
    private final SelenideElement checkbox = $(By.name("per_swimlane_task_limits"));
    private final SelenideElement formTaskLimit = $(By.id("form-task_limit"));
    private final SelenideElement selectFromSrcProject = $(By.id("form-src_project_id"));
    private final SelenideElement submitBtn = $("button[type='submit']");
    private final SelenideElement cancelBtn = $("div.form-actions a");
    private final SelenideElement formError = $(".form-errors");
    private final SelenideElement projectCreationForm = $(By.id("project-creation-form"));

    // fill only necessary fields
    public void fillTheNameAndId(String name, String identifier){
        this.formName.setValue(name);
        this.formIdentifier.setValue(identifier);
    }
}
