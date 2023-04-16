package com.insider.base;

import static com.insider.base.DataManager.getData;

import com.aventstack.extentreports.ExtentTest;
import com.insider.utils.ExtentTestManager;
import java.lang.reflect.Method;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest{

    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);

    @BeforeMethod(alwaysRun = true)
    public void startBrowser(Method method, ITestResult result){
        LOGGER.info("Executing test method : [{}] in class [{}]", result.getMethod().getMethodName(),
            result.getTestClass().getName());
        String nodeName =
            StringUtils.isNotBlank(result.getMethod().getDescription()) ? result.getMethod().getDescription() : method.getName();
        ExtentTest node = ExtentTestManager.getTest().createNode(nodeName);
        ExtentTestManager.setNode(node);
        ExtentTestManager.info("Test Started");
        DriverFactory.launchBrowser();
        WebDriver driver= DriverFactory.getDriver();
        driver.get(getData("url"));
    }

    @AfterMethod()
    public void quitBrowser(){
        DriverFactory.getDriver().quit();
    }
}