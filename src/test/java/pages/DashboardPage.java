package pages;

import helpers.UIHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardPage {
    WebDriver driver;
    UIHelpers uiHelpers;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        uiHelpers = new UIHelpers(driver);
    }

    By headerText = By.xpath("//h4[text()='Dashboard']");
    By projectsBtn = By.xpath("//a[@href='https://rise.fairsketch.com/projects/all_projects']");
    By eventsBtn = By.xpath("//a[@href='https://rise.fairsketch.com/events']");


    public void goToProjectsPage() {
        uiHelpers.waitForPageLoaded();
        Assert.assertTrue(uiHelpers.verifyTextElement(headerText, "Dashboard"), "the dashboard page hasn't loaded");
        uiHelpers.clickElement(projectsBtn);
    }

    public void goToEventsPage() {
        uiHelpers.waitForPageLoaded();
        Assert.assertTrue(uiHelpers.verifyTextElement(headerText, "Dashboard"), "the Events page hasn't loaded");
        uiHelpers.clickElement(eventsBtn);
    }
}
