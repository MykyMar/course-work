package cucumberExamples;


import com.codeborne.selenide.Configuration;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import utils.WebDriverFactory;


@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        features = "src/test/resources/features", //loginTests.feature createProjectTests.feature
        glue = {"cucumberExamples.stepDefinitions"}
)

public class RunCucumberTest extends AbstractTestNGCucumberTests {

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) {
        WebDriverFactory.setUpBrowser(browser);
        Configuration.baseUrl = "http://localhost/";
    }

    @Override
    @DataProvider()
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
