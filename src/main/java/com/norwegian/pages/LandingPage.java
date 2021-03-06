package com.norwegian.pages;

import com.norwegian.utilities.BrowserUtils;
import com.norwegian.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
    public LandingPage(){
        PageFactory.initElements(Driver.getDriver(), this);}

    @FindBy(css = "div[data-code='dates']")
    @CacheLookup
    private WebElement datesFieldElement;

    @FindBy(id = "month-view-tab")
    @CacheLookup
    private WebElement monthViewElement;

    @FindBy(xpath = "(//div[@class='btn-group']/parent::li)[1]")
    @CacheLookup
    private WebElement firstAvailableMonthElement;

    @FindBy(css = "div[class='c22_menu -center-md-up']  a[data-action='apply']")
    private WebElement applyElement;

    @FindBy(css = "a[data-action='find-a-cruise']")
    @CacheLookup
    private WebElement findACruiseBtnElement;

    @FindBy(css = "div[class='c22_menu -center-md-up'] span[class='c20_total_item']")
    @CacheLookup
    private WebElement totalNumberElement;

    @FindBy(xpath = "(//div[@class='btn-group']/parent::li)[1]/parent::ul/parent::div/parent::div/div[@class='c27_header']")
    private WebElement calendarYearElement;

    // Getters
    public String getTotalNumberText(){
        return totalNumberElement.getText();
    }

    public String getMonth(){
        return firstAvailableMonthElement.getText();
    }

    public String getCalendarYear(){
        return calendarYearElement.getText();
    }

    // Actions
    public void clickDates() {
        BrowserUtils.waitForClickablility(datesFieldElement,5000);
        datesFieldElement.click();
    }

    public void selectMonthView(){
        monthViewElement.click();
    }

    public void clickFirstAvailableMonth(){
        firstAvailableMonthElement.click();
    }

    public void clickApply(){
        applyElement.click();
    }

    public void clickFindACruise(){
        findACruiseBtnElement.click();
    }
}
