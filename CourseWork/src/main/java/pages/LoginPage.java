package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;


@Getter

public class LoginPage {
    private final SelenideElement userNameField = $(By.id("form-username"));
    private final SelenideElement passwordField = $(By.id("form-password"));
    private final SelenideElement submitBtn = $("button[type='submit']");
    private final SelenideElement alertMessage = $(".alert.alert-error");



    public DashboardPage login(String userName, String password) {
        open("/login");
        userNameField.setValue(userName);
        passwordField.setValue(password);
        submitBtn.click();
        return new DashboardPage();
    }
}
