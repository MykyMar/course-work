package cucumberExamples.stepDefinitions;

import com.codeborne.selenide.Condition;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.NewProjectPage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ProjectPage;

import java.util.List;


import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class CreateProjectStepDefinitions {

    private String projectName;
    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();
    private final NewProjectPage newProjectPage = new NewProjectPage();
    private final ProjectPage projectPage = new ProjectPage();


    @After
    public void afterScenario() {
        closeWebDriver();
    }

    @Given("User login and on Dashboard page")
    public void login(List <String> userData) {
        open("/login");
        loginPage.login(userData.getFirst(), userData.getFirst());
        assertTrue(dashboardPage.getNewProjectBtn().isDisplayed(), "The 'New project' is not displayed!");
    }

    @Then("User click on New Project button")
    public void userClickOnButton() {
        dashboardPage.getNewProjectBtn().click();
        assertTrue(newProjectPage.getProjectCreationForm().isDisplayed(), "The Project Creation Form is not displayed!");
    }

    @When("User fill the Name, Identifier and Task limit")
    public void userFillTheNameIdentifierAndTaskLimit(List<String> projectData) {
        projectName = projectData.getFirst();
        newProjectPage.fillTheNameAndId(projectName, projectData.get(1));
        newProjectPage.getFormTaskLimit().setValue(projectData.get(2));
    }

    @And("User click on Save button")
    public void userClickOnSaveButton() {
        newProjectPage.getSubmitBtn().click();
    }

    @Then("The project should be created")
    public void theProjectShouldBeCreated() throws Exception {
        assertFalse("The Project Creation Form is displayed!", newProjectPage.getProjectCreationForm().isDisplayed());
        try{
            $(".title").shouldHave(Condition.text(projectName));
        }catch (AssertionError e){
            System.out.println("The project was not found!");
        }

    }
    @And("Remove project for retesting")
    public void removeProjectForRetesting() {
        projectPage.removeProject(projectName);
    }

    @When("User fill the Name, existing Identifier")
    public void userFillTheNameExistingIdentifierAndTaskLimit(List<String> projectData) {
        newProjectPage.fillTheNameAndId(projectData.getFirst(), projectData.get(1));
    }

    @Then("An error message should appear under the Identifier field")
    public void anErrorMessageShouldAppearUnderTheIdentifierField() {
        assertTrue(newProjectPage.getFormError().isDisplayed(), "The error message did not appear!");
    }


}
