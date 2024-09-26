package ui;

import api.ProjectApiProceduresTests;
import api.UserApiProceduresTests;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.TaskPage;
import utils.WebDriverFactory;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;




public class TaskActionsTests {
    LoginPage loginPage = new LoginPage();
    TaskPage taskPage = new TaskPage();
    private final String taskName = "Create new Task for coursework.";
    private int projectId;
    private final String userName = UserApiProceduresTests.getUsername();


    @BeforeClass(dependsOnGroups = "preconditions")
    @Parameters("browser")
    public void Preconditions(String browser) {
        WebDriverFactory.setUpBrowser(browser);
        Configuration.baseUrl = "http://localhost:80/";
        projectId = ProjectApiProceduresTests.getProjectId();
        loginPage.login(userName, userName);
    }


    @AfterClass(dependsOnGroups = "postconditions")
    public void postconditions() {
        closeWebDriver();
    }

    @Test(dependsOnGroups = "preconditions")
    public void createTask() {
        open("/board/" + projectId);
        taskPage.createTask(taskName);
        taskPage.getBacklogColum().shouldHave(Condition.text(taskName));
        taskPage.getTaskByTitle(taskName).click();
    }

    @Test(dependsOnMethods = "createTask")
    public void createSubTask() {
        taskPage.createSubTask("Sub-task");
        $(".subtasks-table").shouldHave(Condition.text("Sub-task"));
    }

    @Test(dependsOnMethods = "createTask")
    public void addComment() {
        taskPage.addComment("Comment");
        $(".comment-content").shouldHave(Condition.text("Comment"));
    }

    @Test(dependsOnMethods = "createTask")
    public void moveTask() {
        open("/board/" + projectId);
        actions()
                .clickAndHold(taskPage.getTaskByTitle(taskName))
                .moveToElement(taskPage.getInProgressColumn())
                .release().build().perform();
        taskPage.getInProgressColumn().shouldHave(Condition.text(taskName));

    }

    @Test(dependsOnMethods = "createTask", priority = 2)
    public void closeTask() throws InterruptedException {
        taskPage.getTaskByTitle(taskName).click();
        taskPage.getCloseTaskBtn().click();
        taskPage.getCloseTaskYes().click();

        //added waiting for the list of elements to be correct
        Thread.sleep(1000);
        List<String> elements = $$("a.js-modal-confirm").texts();
        boolean found = false;

        for (String element : elements) {
            if (element.contains("Open this task")) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "The task is still open!");
    }
}
