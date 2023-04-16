package com.insider.listener;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.insider.base.DriverFactory;
import com.insider.utils.ExtentManager;
import com.insider.utils.ExtentTestManager;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

  private static final Logger LOGGER = LogManager.getLogger(TestListener.class);

  @Override
  public void onTestSuccess(ITestResult result) {
    LOGGER.info("Passed test method : [{}] in class [{}]", result.getMethod().getMethodName(), result.getTestClass().getName());
    ExtentTestManager.getNode().pass("Test Passed");
  }

  @Override
  public void onTestFailure(ITestResult result) {
    LOGGER.info("Failed test method : [{}] in class [{}]", result.getMethod().getMethodName(),
        result.getTestClass().getName());
    ExtentTestManager.getNode().fail("Test failed");
    LOGGER.error(result.getThrowable());
  }

  @Override
  public void onStart(ITestContext context) {
    LOGGER.info("Executing test : [{}] from class [{}]", context.getCurrentXmlTest().getName(),
        context.getCurrentXmlTest().getXmlClasses().get(0).getName());
    ExtentTestManager.startTest(context.getName(), "Test started");
  }

  @Override
  public void onFinish(ITestContext context) {
    ExtentManager.getExtentReports().flush();
  }
}
