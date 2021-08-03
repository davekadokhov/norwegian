package com.norwegian.pages;

import com.norwegian.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class VacationsPage {
    public VacationsPage(){
        PageFactory.initElements(Driver.getDriver(), this);}

    @FindBy(id = "simplemodal-close-img")
    @CacheLookup
    private WebElement closeModalElement;

    @FindBy(css ="a[aria-label='View Cruise']")
    @CacheLookup
    private List<WebElement> viewCruiseBtns;

    @FindBy(css = ".c195_title_item")
    private List<WebElement> headlineElements;

    @FindBy(css = "li[class='c282_list_item']")
    private List<WebElement> sailingDates;

    // Getters
    public List<String> getHeadlineText(){
        List<String> values = new ArrayList<>();
        for(WebElement element : headlineElements){
            values.add(element.getText());
        }
        return values;
    }

    public List<String> getSailingDatesText(){
        List<String> values = new ArrayList<>();
        for(WebElement element : sailingDates){
            values.add(element.getText());
        }
        return values;
    }

    public int getTotalNumberOfCruises(){
        return Integer.parseInt(getHeadlineText().get(1));
    }

    // Actions
    public void closeModal(){
        closeModalElement.click();
    }

    public int numberOfVacations(){
        return viewCruiseBtns.size();
    }
}
