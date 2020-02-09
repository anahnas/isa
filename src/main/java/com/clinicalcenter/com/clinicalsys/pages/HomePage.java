package com.clinicalcenter.com.clinicalsys.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;

    // prvi deo
    @FindBy(css = "#email")
    private WebElement email;

    @FindBy(css = "#password")
    private WebElement password;

    @FindBy(css = "#loginBtn")
    private WebElement loginBtn;

    @FindBy(css = "#scheduleAppA")
    private WebElement scheduleAppLink;


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getScheduleAppLink() {
        return scheduleAppLink;
    }

    public void setScheduleAppLink(WebElement scheduleAppLink) {
        this.scheduleAppLink = scheduleAppLink;
    }

    public WebElement getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email.clear();
        this.email.sendKeys(email);
    }

    public WebElement getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password.clear();
        this.password.sendKeys(password);
    }

    public WebElement getLoginBtn() {
        return loginBtn;
    }

    public void setLoginBtn(WebElement loginBtn) {
        this.loginBtn = loginBtn;
    }

    public void ensureLoginBtnIsClickable(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(loginBtn));
    }

    public void ensureScheduleButtonLinkIsClickable(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(scheduleAppLink));
    }


}
