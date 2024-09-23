package pages;

import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.By;


import static com.codeborne.selenide.Selenide.$;


public class ProjectPage {
    private final SelenideElement remove = $(By.linkText("Remove"));
    private final SelenideElement removeConfirm = $(By.id("modal-confirm-button"));
    private final DashboardPage dashboardPage = new DashboardPage();

    public void removeProject(String projectName){   // user on /project/{id} page
        remove.click();
        removeConfirm.click();
       if (dashboardPage.isProjectNameExist(projectName)) {
           System.out.println("Something went wrong, the project still exists.");
       } else System.out.println("Project deleted");
    }

}
