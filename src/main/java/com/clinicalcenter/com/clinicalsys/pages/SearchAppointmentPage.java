package com.clinicalcenter.com.clinicalsys.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchAppointmentPage {

    private WebDriver driver;

    // elements

    // prvi deo
    @FindBy(css = "#selectedType")
    private WebElement selectedTypeInputField;

    @FindBy(css = "#startDate")
    private WebElement startDateInputField;

    @FindBy(css = "#searchBtn")
    private WebElement searchBtn;

    // drugi deo
    @FindBy(css = "#filterClinicsForm")
    private WebElement filterClinicsForm;

    @FindBy(css = "#filterClinicsBtn")
    private WebElement filterClinicsBtn;

    @FindBy(css = "#maximumPriceInputField")
    private WebElement maximumPriceInputField;

    @FindBy(css = "#clinicsTable")
    private WebElement clinicsTable;

    // treci deo
    @FindBy(css = "#searchDoctorsForm")
    private WebElement searchDoctorsForm;

    @FindBy(css = "#filterDoctorsBtn")
    private WebElement filterDoctorsBtn;

    @FindBy(css = "#doctorsTable")
    private WebElement doctorsTable;

    @FindBy(css = "[name=\"scheduleBtn\"]")
    private WebElement scheduleBtn;

    // getters, setters and constructor

    public SearchAppointmentPage(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement getMaximumPriceInputField() {
        return maximumPriceInputField;
    }


    public void setMaximumPriceInputField(String maximumPrice) {
        this.maximumPriceInputField.clear();
        this.maximumPriceInputField.sendKeys(maximumPrice);
    }

    public WebElement getScheduleBtn() {
        return scheduleBtn;
    }

    public void setScheduleBtn(WebElement scheduleBtn) {
        this.scheduleBtn = scheduleBtn;
    }

    public WebElement getFilterClinicsForm() {
        return filterClinicsForm;
    }

    public void setFilterClinicsForm(WebElement filterClinicsForm) {
        this.filterClinicsForm = filterClinicsForm;
    }

    public WebElement getFilterClinicsBtn() {
        return filterClinicsBtn;
    }

    public void setFilterClinicsBtn(WebElement filterClinicsBtn) {
        this.filterClinicsBtn = filterClinicsBtn;
    }

    public WebElement getSearchDoctorsForm() {
        return searchDoctorsForm;
    }

    public void setSearchDoctorsForm(WebElement searchDoctorsForm) {
        this.searchDoctorsForm = searchDoctorsForm;
    }

    public WebElement getFilterDoctorsBtn() {
        return filterDoctorsBtn;
    }

    public void setFilterDoctorsBtn(WebElement filterDoctorsBtn) {
        this.filterDoctorsBtn = filterDoctorsBtn;
    }

    public WebElement getDoctorsTable() {
        return doctorsTable;
    }

    public void setDoctorsTable(WebElement doctorsTable) {
        this.doctorsTable = doctorsTable;
    }

    public WebElement getSelectedTypeInputField() {
        return selectedTypeInputField;
    }


    public WebElement getfilterClinicsBtn() {
        return filterClinicsBtn;
    }

    public void setfilterClinicsBtn(WebElement filterClinicsBtn) {
        this.filterClinicsBtn = filterClinicsBtn;
    }

    public WebElement getClinicsTable() {
        return clinicsTable;
    }

    public void setClinicsTable(WebElement clinicsTable) {
        this.clinicsTable = clinicsTable;
    }

    public void setSelectedTypeInputField() {
        this.selectedTypeInputField.click();
        driver.findElement(By.className("appTypeOptions")).click();
    }

    public WebElement getStartDateInputField() {
        return startDateInputField;
    }

    public void setStartDateInputField(String date) {
        this.startDateInputField.clear();
        this.startDateInputField.click();
        this.startDateInputField.sendKeys(date);
    }

    public WebElement getSearchBtn() {
        return searchBtn;
    }

    public void setSearchBtn(WebElement searchBtn) {
        this.searchBtn = searchBtn;
    }

    public WebElement getfilterClinicsForm() {
        return filterClinicsForm;
    }


    public void setfilterClinicsForm(WebElement filterClinicsForm) {
        this.filterClinicsForm = filterClinicsForm;
    }



    // ensurements

    public void ensureFilterClinicsFormIsDisplayed(){
        new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOf(filterClinicsForm));
    }

    public void ensureFilterClinicsFormIsNotDisplayed(){
        new WebDriverWait(driver, 50).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("filterClinicsForm"),0));
    }


    public void ensureSearchButtonIsClickable(){
        new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(searchBtn));
    }


    public void ensureThereAreXAppTypeOptions(int x){
        new WebDriverWait(driver, 59).until(ExpectedConditions.numberOfElementsToBe(By.className("appTypeOptions"),x));
    }


    public void ensureFilterClinicsBtnIsClickable(){
        new WebDriverWait(driver, 59).until(ExpectedConditions.elementToBeClickable(filterClinicsBtn));
    }

    public void ensureClinicsTableIsDisplayed(){
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(clinicsTable));
    }


    public void ensureClinicsTableIsNotDisplayed(){
        new WebDriverWait(driver, 50).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("#clinicsTable"), 0));
    }


    public void ensureThereAreMoreThanXNumberOfElementsInClinicsTable(int x){
        new WebDriverWait(driver, 50).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("ClinicRow"), x));
        new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.className("doctorsBtn")));
    }

    public void ensureFilterDoctorsFormIsDisplayed(){
        new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOf(searchDoctorsForm));
    }

    public void ensureDoctorsTableIsDisplayed(){
        new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOf(doctorsTable));
    }

    public void ensureDoctorsTableIsNotDisplayed(){
        new WebDriverWait(driver, 50).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(doctorsTable)));
    }

    public void ensureThereAreMoreThanXNumberOfElementsInDoctorsTable(int x){
        new WebDriverWait(driver, 50).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("DoctorRow"), x));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,800)");
        new WebDriverWait(driver, 59).until(ExpectedConditions.elementToBeClickable(scheduleBtn));
    }

    public void setStartAppTimeOption(){
        new WebDriverWait(driver, 50).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("startAppTime"), 0));
        driver.findElement(By.id("orders")).click();
        driver.findElement(By.className("startAppTime")).click();
    }
}
