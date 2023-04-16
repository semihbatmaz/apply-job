package com.insider.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {

  private static final Logger LOGGER = LogManager.getLogger(SuiteListener.class);
  public static String xmlSuiteFileName;

  @Override
  public void onStart(ISuite suite) {
    String[] suiteFileNameArray = suite.getXmlSuite().getFileName().split("test-suite");
    xmlSuiteFileName = suiteFileNameArray[suiteFileNameArray.length-1].substring(2);
    LOGGER.info("Starting test suite [{}] | XML Suite file : [{}]", suite.getName(), suite.getXmlSuite().getFileName());
  }

  @Override
  public void onFinish(ISuite suite) {
    LOGGER.info("Finished test suite [{}] | XML Suite file : [{}]", suite.getName(), suite.getXmlSuite().getFileName());
  }
}
