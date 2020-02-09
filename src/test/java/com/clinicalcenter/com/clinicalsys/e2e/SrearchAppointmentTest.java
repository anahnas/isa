package com.clinicalcenter.com.clinicalsys.e2e;

import com.clinicalcenter.com.clinicalsys.pages.HomePage;
import com.clinicalcenter.com.clinicalsys.pages.SearchAppointmentPage;
import com.clinicalcenter.com.clinicalsys.e2e.config.AppConstants;
import com.clinicalcenter.com.clinicalsys.e2e.config.LoginConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class SrearchAppointmentTest {
    private WebDriver browser;

    private SearchAppointmentPage searchAppointmentPage;
    private HomePage homePage;


    @BeforeMethod
    public void setupSelenium() {
        System.setProperty("webdriver.chrome.driver", AppConstants.CHROME_DRIVER_PATH);
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.navigate().to(AppConstants.FRONTEND_APP_URL);

        searchAppointmentPage = PageFactory.initElements(browser, SearchAppointmentPage .class);
        homePage = PageFactory.initElements(browser, HomePage.class);
    }


    @AfterMethod
    public void shutdownSelenium() {
        browser.quit();
    }


    public void gotoScheduleAppointmentPage(){
        homePage.setEmail(LoginConstants.EMAIL);
        homePage.setPassword(LoginConstants.PASSWORD);
        homePage.ensureLoginBtnIsClickable();

        homePage.getLoginBtn().click();
        homePage.ensureScheduleButtonLinkIsClickable();

        browser.navigate().to(browser.getCurrentUrl() + "scheduleApp");
        searchAppointmentPage.ensureSearchButtonIsClickable();
        searchAppointmentPage.ensureThereAreXAppTypeOptions(10);
    }

   /* @Test
    public void ensureAllIsDisplayed(){
        this.gotoScheduleAppointmentPage();
    }

    @Test
    public void whenNothingInSearchInput_noSearchIsPerformed(){
        this.gotoScheduleAppointmentPage();
        // by default, fields are empty
        searchAppointmentPage.getSearchBtn().click();
        browser.switchTo().alert().accept();
        searchAppointmentPage.ensureClinicsTableIsNotDisplayed();
        searchAppointmentPage.ensureFilterClinicsFormIsNotDisplayed();
    }

    @Test
    public void whenRightSearchInput_searchClinicsIsPerformed(){
        this.gotoScheduleAppointmentPage();
        // by default, fields are empty
        searchAppointmentPage.setSelectedTypeInputField();
        searchAppointmentPage.getStartDateInputField().click();
        searchAppointmentPage.getStartDateInputField().sendKeys(Keys.ARROW_LEFT, Keys.ARROW_LEFT, Keys.ARROW_LEFT, Keys.ARROW_UP, Keys.ARROW_UP, Keys.ARROW_UP);
        searchAppointmentPage.getStartDateInputField().sendKeys(Keys.ARROW_RIGHT, Keys.ARROW_UP, Keys.ARROW_RIGHT, Keys.ARROW_UP);
        searchAppointmentPage.getSearchBtn().click();
        searchAppointmentPage.ensureClinicsTableIsDisplayed();
        searchAppointmentPage.ensureFilterClinicsFormIsDisplayed();
        searchAppointmentPage.ensureFilterClinicsBtnIsClickable();
        searchAppointmentPage.setMaximumPriceInputField("5000");
        searchAppointmentPage.getFilterClinicsBtn().click();
        searchAppointmentPage.ensureThereAreMoreThanXNumberOfElementsInClinicsTable(0);
        searchAppointmentPage.getClinicsTable().findElement(By.className("doctorsBtn")).click();
        searchAppointmentPage.ensureDoctorsTableIsDisplayed();
        searchAppointmentPage.ensureFilterDoctorsFormIsDisplayed();
        searchAppointmentPage.ensureThereAreMoreThanXNumberOfElementsInDoctorsTable(0);
        searchAppointmentPage.setStartAppTimeOption();
        searchAppointmentPage.getScheduleBtn().click();
        new WebDriverWait(browser, 10).until(ExpectedConditions.alertIsPresent());
        assertTrue(browser.switchTo().alert().getText().contains("Request for the Appointment successfully sent"));
    }
*/
}
