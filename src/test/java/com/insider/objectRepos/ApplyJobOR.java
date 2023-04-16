package com.insider.objectRepos;

import org.openqa.selenium.By;

public class ApplyJobOR {

    public static final By ACCEPT_COOKIES_BUTTON= By.id("wt-cli-accept-all-btn");
    public static final By NAVIGATION_BAR_MORE_BUTTON= By.xpath("//a/span[contains(text(),'More')]");
    public static final By CAREER_AREA= By.xpath("//a/h5[contains(text(),'Careers')]");

    //Career Page
    public static final By CAREER_PAGE_OUR_LOCATION_HEADER= By.xpath("//h3[contains(text(),'Our Locations')]");
    public static final By CAREER_PAGE_LOCATION_SLIDER= By.id("location-slider");
    public static final By CAREER_PAGE_LIFE_AT_INSIDER_HEADER= By.xpath("//h2[contains(text(),'Life at Insider')]");
    public static final By CAREER_PAGE_SEE_ALL_TEAMS_BUTTON= By.xpath("//a[contains(text(),'See all teams')]");
    public static final By CAREER_PAGE_PRODUCT_ENGINEERING_BUTTON= By.xpath("//h3[contains(text(),'Product & Engineering')]");
    public static final By CAREER_PAGE_QUALITY_ASSURANCE_JOBS= By.xpath("//div[@class='job-title mt-0 mt-lg-2 mt-xl-5']//h3[contains(text(),'Quality Assurance')]");

    //Quality Assurance Page
    public static final By QA_PAGE_SEE_ALL_QA_PAGE_BUTTON= By.xpath("//a[contains(text(),'See all QA jobs')]");
    public static final By FILTER_BY_LOCATION_DROPDOWN= By.id("select2-filter-by-location-container");
    public static final By ISTANBUL_LOCATION_AREA= By.xpath("//li[contains(text(),'Istanbul, Turkey')]");
    public static final By JOB_LISTS_AREA= By.xpath("//div[@id='jobs-list']/div");
    public static final By OPEN_POSITIONS_JOB_TITLE= By.xpath("//p[@class='position-title font-weight-bold']");
    public static final By OPEN_POSITIONS_JOB_DEPARTMENT= By.xpath("//span[@class='position-department text-large font-weight-600 text-primary']");
    public static final By OPEN_POSITIONS_JOB_LOCATION= By.xpath("//div[@class='position-location text-large']");
    public static final By APPLY_NOW_BUTTON= By.xpath("//*[@id=\"jobs-list\"]/div[1]/div/a");
    public static final By ANKARA_LOCATION_AREA= By.xpath("//div[contains(text(),'Ankara')]");

}
