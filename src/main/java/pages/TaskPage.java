package pages;

import com.codeborne.selenide.SelenideElement;

import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

@Getter
public class TaskPage {
    //some selectors have a complex structure due to the fact that the tested site is not adapted properly.
    private final SelenideElement createTaskBtn = $$("a[title='Add a new task']").get(0);
    private final SelenideElement title = $(By.id("form-title"));
    private final SelenideElement submitBtn = $("button[type='submit']");
    private final SelenideElement addSubTaskBtn = $x("//a[@class='js-modal-medium' and contains(text(),'Add a sub-task')]");
    private final SelenideElement closeTaskBtn = $x("//a[@class='js-modal-confirm' and contains(text(),'Close this task')]");
    private final SelenideElement closeTaskYes = $(By.id("modal-confirm-button"));
    private final SelenideElement addCommentBtn = $x("//a[contains(@class, 'js-modal-small') and contains(text(), 'Add a comment')]");
    private final SelenideElement commentField = $$("textarea[aria-label='New comment']").get(1);
    private final SelenideElement backlogColum = $x("//*[@id='board']/tbody/tr[2]/td[1]/div[1]");
    private final SelenideElement inProgressColumn = $x("//*[@id='board']/tbody/tr[2]/td[3]/div[1]");

    public SelenideElement getTaskByTitle(String taskTitle) {
        return $x(String.format("//a[text()='%s']", taskTitle));
    }

    //due to duplicate elements on the site, I had to separate the functions of creating
    public void createTask(String taskTitle) {
        createTaskBtn.click();
        title.setValue(taskTitle);
        submitBtn.click();
    }

    public void createSubTask(String subTaskTitle) {
        SelenideElement submit = $$("button[type='submit']").get(1);
        addSubTaskBtn.click();
        title.setValue(subTaskTitle);
        submit.click();
    }

    public void addComment(String comment) {
        SelenideElement submit = $$("button[type='submit']").get(1);
        addCommentBtn.click();
        commentField.setValue(comment);
        submit.click();
    }


}
