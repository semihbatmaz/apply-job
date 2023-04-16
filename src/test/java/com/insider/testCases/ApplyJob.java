package com.insider.testCases;


import static com.insider.base.DataManager.getData;
import static com.insider.base.Page.checkIfWebElementExist;
import static com.insider.base.Page.checkTitle;
import static com.insider.base.Page.click;
import static com.insider.base.Page.clickElementByLocation;
import static com.insider.base.Page.clickIfExists;
import static com.insider.base.Page.getElementsSize;
import static com.insider.base.Page.getElementsText;
import static com.insider.base.Page.getUrlAddress;
import static com.insider.base.Page.scrollIntoView;
import static com.insider.base.Page.sleepInSeconds;
import static com.insider.base.Page.switchToTab;
import static com.insider.base.Page.waitUntilWebElementIsInvisible;
import static com.insider.base.Page.waitUntilWebElementIsVisible;
import static com.insider.objectRepos.ApplyJobOR.*;

import com.insider.base.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ApplyJob extends BaseTest {

    @Test(description = "Apply quality assurance job at Insider", priority = 1)
    public void applyQualityAssuranceJob() {

        clickIfExists(ACCEPT_COOKIES_BUTTON);

        // 1-Visit https://useinsider.com/ and check Insider home page is opened or not
        checkTitle(getData("mainPageTitle"));

        // 2-Select “More” menu in navigation bar, select “Careers” and check Career page, its Locations, Teams and Life at Insider blocks are opened or not
        click(NAVIGATION_BAR_MORE_BUTTON);
        click(CAREER_AREA);
        Assert.assertEquals(getUrlAddress(),getData("careerPageUrl"), "Career page could not loaded");
        Assert.assertTrue(checkIfWebElementExist(CAREER_PAGE_OUR_LOCATION_HEADER));
        Assert.assertTrue(checkIfWebElementExist(CAREER_PAGE_LOCATION_SLIDER));
        Assert.assertTrue(checkIfWebElementExist(CAREER_PAGE_LIFE_AT_INSIDER_HEADER));

        // 3-Click “See All Teams”, select Quality Assurance, click “See all QA jobs”, filter jobs byLocation - Istanbul, Turkey and department - Quality Assurance, check presence of jobs list
        scrollIntoView(CAREER_PAGE_PRODUCT_ENGINEERING_BUTTON);
        clickElementByLocation(CAREER_PAGE_SEE_ALL_TEAMS_BUTTON,50,10);
        waitUntilWebElementIsVisible(CAREER_PAGE_QUALITY_ASSURANCE_JOBS);
        click(CAREER_PAGE_QUALITY_ASSURANCE_JOBS);
        click(QA_PAGE_SEE_ALL_QA_PAGE_BUTTON);
        waitUntilWebElementIsVisible(JOB_LISTS_AREA);
        waitUntilWebElementIsVisible(FILTER_BY_LOCATION_DROPDOWN);
        click(FILTER_BY_LOCATION_DROPDOWN);
        waitUntilWebElementIsVisible(ISTANBUL_LOCATION_AREA);
        click(ISTANBUL_LOCATION_AREA);
        waitUntilWebElementIsVisible(OPEN_POSITIONS_JOB_TITLE);
        waitUntilWebElementIsInvisible(ANKARA_LOCATION_AREA,6);
        Assert.assertTrue(checkIfWebElementExist(OPEN_POSITIONS_JOB_TITLE));

        // 4-Check that all jobs’ Position contains “Quality Assurance”, Department contains “Quality Assurance”, Location contains “Istanbul, Turkey”
        for (int i = 0; i <getElementsSize(JOB_LISTS_AREA) ; i++) {
            Assert.assertTrue(getElementsText(OPEN_POSITIONS_JOB_TITLE).get(i).contains("Quality Assurance"),"Job title could not be found");
            Assert.assertTrue(getElementsText(OPEN_POSITIONS_JOB_DEPARTMENT).get(i).contains("Quality Assurance"),"Job departments could not be found");
            Assert.assertTrue(getElementsText(OPEN_POSITIONS_JOB_LOCATION).get(i).contains("Istanbul, Turkey"),"Job locations could not be found");
        }

        // 5-Click “Apply Now” button and check that this action redirects us to Lever Application form page
        scrollIntoView(FILTER_BY_LOCATION_DROPDOWN);
        clickElementByLocation(APPLY_NOW_BUTTON,33,25);
        switchToTab(1);
        Assert.assertTrue(getUrlAddress().contains("jobs.lever.co"),"Url address could not be redirected to lever.job.co");
    }

}
