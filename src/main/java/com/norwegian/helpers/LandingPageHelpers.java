package com.norwegian.helpers;

import com.norwegian.pages.LandingPage;
import com.norwegian.pages.VacationsPage;
import org.testng.Assert;

public class LandingPageHelpers {
    LandingPage landingPage = new LandingPage();
    VacationsPage vacationsPage = new VacationsPage();

    public LandingPageHelpers selectFirstMonth(){
        landingPage.clickDates();
        landingPage.clickFirstAvailableMonth();
        landingPage.clickApply();
        landingPage.clickFindACruise();
        return this;
    }

    public LandingPageHelpers verifyResults(){
        try{ vacationsPage.closeModal(); }
        catch (Exception ex) {  } // modal didn't exist

        Assert.assertEquals(vacationsPage.numberOfVacations(),vacationsPage.getTotalNumberOfCruises());

        return this;
    }
}
