package utils;

import com.codeborne.selenide.Configuration;


public class WebDriverFactory {


    public static void setUpBrowser(String browser) {
        System.out.println("Running tests on: " + browser);
        Configuration.browserSize = "1920x1080";
        switch (browser.toLowerCase()) {
            case "firefox":
                Configuration.browser = "firefox";
                break;
            case "chrome":
                Configuration.browser = "chrome";
                break;
            case "chrome-headless":
                Configuration.browser = "chrome";
                Configuration.headless = true;
                break;
            default:
                Configuration.browser = "firefox";
                throw new RuntimeException("Unsupported WebDriver:" + browser);
        }
    }

}
