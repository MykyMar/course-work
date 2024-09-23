package cucumberExamples.stepDefinitions;


import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.annotations.Parameters;
import pages.DashboardPage;
import pages.LoginPage;
import utils.WebDriverFactory;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.testng.AssertJUnit.assertTrue;


public class LoginStepDefinitions {
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();


    @After
    public void afterScenario() {
        closeWebDriver();
    }

    @Given("URL to login page opened in browser")
    public void openUrl() {

        open("/login");
    }

    @When("The user enter user name and password")
    public void theUserEnterUserNameAndPassword(List<String> userData) {
        loginPage.getUserNameField().setValue(userData.getFirst());
        loginPage.getPasswordField().setValue(userData.getFirst());
    }

    @When("The user enter user name {word} and password {word}")
    public void theUserEnterUserNameUserNameAndPasswordPassword(String userName, String password) {
        loginPage.getUserNameField().setValue(userName);
        loginPage.getPasswordField().setValue(password);
    }

    @And("The user click on login button")
    public void userClickOnLoginButton() {
        loginPage.getSubmitBtn().click();

    }

    @Then("The Dashboard page should be displayed for valid credentials")
    public void dashboardPageForValidCredentials() {
        assertTrue("The 'New project' is not displayed!", dashboardPage.getNewProjectBtn().isDisplayed());
    }


    @Then("An error message should appear for invalid credentials")
    public void anErrorMessageShouldAppear() {
        loginPage.getAlertMessage()
                .shouldBe(visible, Duration.ofMillis(500))
                .shouldHave(text("Bad username or password"));
    }

}
