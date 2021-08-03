package com.norwegian.tests;

import com.norwegian.helpers.LandingPageHelpers;
import com.norwegian.utilities.TestBase;
import org.testng.annotations.Test;

public class LandingPageTests extends TestBase {
    private LandingPageHelpers ncl = new LandingPageHelpers();

    @Test(groups= {"regression"})
    public void verifyDashboards() {

        ncl.selectFirstMonth();

        String month = ncl.getMonth();
        String year = ncl.getYear();
        String date = month + "," + year;

        ncl.clickApplyAndFindCruise()
                .verifyResults(date);
        System.out.println("----pass----");
    }
}
