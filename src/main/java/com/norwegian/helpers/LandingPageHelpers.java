package com.norwegian.helpers;

import com.norwegian.pages.LandingPage;
import com.norwegian.pages.VacationsPage;
import org.testng.Assert;

public class LandingPageHelpers {
    LandingPage landingPage = new LandingPage();
    VacationsPage vacationsPage = new VacationsPage();

    public LandingPageHelpers selectFirstMonth() {
        landingPage.clickDates();
        landingPage.clickFirstAvailableMonth();
        return this;
    }

    public String getMonth(){
        return landingPage.getMonth();
    }

    public String getYear(){
        return landingPage.getCalendarYear();
    }

    public LandingPageHelpers clickApplyAndFindCruise() {
        landingPage.clickApply();
        landingPage.clickFindACruise();
        return this;
    }

    public LandingPageHelpers verifyResults(String date){
        try{ vacationsPage.closeModal(); }
        catch (Exception ex) {  } // modal didn't exist

        Assert.assertEquals(vacationsPage.numberOfVacations(),vacationsPage.getTotalNumberOfCruises());
        for(String each : vacationsPage.getSailingDatesText()){
            Assert.assertEquals(each,date,"Dates does not match");
        }
        return this;
    }
}
