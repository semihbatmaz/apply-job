package com.insider.base;

import static com.insider.base.DataManager.getData;
import static com.insider.objectRepos.ApplyJobOR.CAREER_PAGE_SEE_ALL_TEAMS_BUTTON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Page {
    private static final Logger LOGGER = LogManager.getLogger(Page.class);

    public static void click(By by) {
        boolean elementFound = false;
        int counter = 0;
        while (counter <= 30) {
            try {
                WebElement element = DriverFactory.getDriver().findElement(by);
                element.click();
                elementFound = true;
                counter = counter + 1;
                if (elementFound){
                    break;
                }
            }
            catch (Exception e) {
                LOGGER.info("Web element [{}] | Click attempt : [{}]", by.toString(), counter);
            }
            if (counter == 30) {
                break;
            }
            counter++;
        }
        if (!elementFound) {
            // Final attempt: if fails, will throw the exception as is
            DriverFactory.getDriver().findElement(by).click();
        }
    }

    public static boolean waitUntilWebElementIsVisible (By by){
        boolean elementExists = false;
        sleepInSeconds(3);
        try {
            int counter = 0;
            while (counter <= 20) {
                if (checkIfWebElementExist(by)) {
                    LOGGER.info("Web element [{}] became visible attempt:  [{}]", by.toString(), counter);
                    elementExists= true;
                    break;
                } else {
                    LOGGER.info("Web element [{}] is still invisible. Waiting to become visible. Attempt:  [{}]", by.toString(), counter);
                    sleepInSeconds(2);
                    counter++;
                }
            }
        }
        catch (Exception e) {
            LOGGER.warn(e);
        }
        return elementExists;
    }

    public static boolean checkIfWebElementExist(By by) {
        try {
            WebElement element = DriverFactory.getDriver().findElement(by);
            if (element.isDisplayed() && element.isEnabled()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static void sleepInSeconds(int seconds){
        try {
            LOGGER.debug("Wait for seconds: " + seconds);
            Thread.sleep(seconds*1000L);
        }
        catch (Exception e){
        }
    }

    public static boolean checkTitle(String expectedTitle){
        if (DriverFactory.getDriver() != null && DriverFactory.getDriver().getTitle().contains(expectedTitle)) {
            LOGGER.info("Web page title is correct: " + getData("url"));
            return true;
        }
        else{
            LOGGER.info("Web page could not open");
            return false;
        }
    }

    public static void scrollIntoView (By by){
        try {
            ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", DriverFactory.getDriver().findElement(by));
            sleepInSeconds(7);
        } catch (Exception e) {
            LOGGER.debug(e);
        }
    }

    public static String getUrlAddress(){
        return DriverFactory.getDriver().getCurrentUrl();
    }

    public static void scrollIntoView (WebElement element){
        try {
            ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            LOGGER.debug(e);
        }
    }

    public static void clickElementByLocation(By by, int xAxisPercentage, int yAxisPercentage){
        WebElement element= DriverFactory.getDriver().findElement(by);
        int x = element.getSize().getWidth()* xAxisPercentage/100;
        int y = element.getSize().getHeight()* yAxisPercentage/100;

        Actions actions = new Actions(DriverFactory.getDriver());
        actions.moveToElement(element).moveByOffset(x,y).click().build().perform();


    }

    public static void switchToTab(int tabNo){
        ArrayList<String> tabs = new ArrayList<String>(DriverFactory.getDriver().getWindowHandles());
        DriverFactory.getDriver().switchTo().window(tabs.get(tabNo));
    }

    public static int getElementsSize (By by){
        return DriverFactory.getDriver().findElements(by).size();
    }

    public static List<String> getElementsText (By by){
        return DriverFactory.getDriver().findElements(by).stream().map(element -> element.getText().trim()).collect(Collectors.toList());
    }


}
