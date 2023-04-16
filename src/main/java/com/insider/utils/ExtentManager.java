package com.insider.utils;

import static com.insider.listener.SuiteListener.xmlSuiteFileName;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.insider.base.DataManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ExtentManager {

  private static final ExtentReports EXTENT_REPORTS = new ExtentReports();

  static {
    String timeStamp = new SimpleDateFormat("dd_MMM_yyy_HH_mm_ss",Locale.ENGLISH).format(new Date());
    String reportFileName = "Test_Report_".concat(timeStamp).concat(".html");
    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent-reports/" + reportFileName);

    htmlReporter.config().setReportName("Insider Automation Test Report");
    htmlReporter.config().setDocumentTitle("Insider Automation Results");
    EXTENT_REPORTS.attachReporter(htmlReporter);
    EXTENT_REPORTS.setSystemInfo("Suite File", xmlSuiteFileName);
    EXTENT_REPORTS.setSystemInfo("Author", "Semih Batmaz");
    EXTENT_REPORTS.setSystemInfo("Environment", DataManager.getData("url"));
    EXTENT_REPORTS.setSystemInfo("Browser", ConfigManager.getBrowser());
    EXTENT_REPORTS.setSystemInfo("Headless", String.valueOf(ConfigManager.isHeadless()));
  }

  private ExtentManager() {
  }

  public static ExtentReports getExtentReports() {
    return EXTENT_REPORTS;
  }
}