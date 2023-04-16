package com.insider.base;

import static com.insider.base.DataManager.getData;

import com.insider.utils.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
  private static final InheritableThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new InheritableThreadLocal<>();

  public static WebDriver getDriver() {
    return WEB_DRIVER_THREAD_LOCAL.get();
  }

  public static void setDriver(WebDriver driver) {
    WEB_DRIVER_THREAD_LOCAL.set(driver);
  }

  public static void launchBrowser(){
    WebDriver driver;
    WebDriverManager.chromedriver().setup();
    ChromeOptions chromeOptions = new ChromeOptions();
    if (ConfigManager.isHeadless()) {
      chromeOptions.addArguments("--no-sandbox");
      chromeOptions.addArguments("--disable-dev-shm-usage");
      chromeOptions.setHeadless(true);
      chromeOptions.addArguments("--window-size=1920,1080");
    }
    else {
      chromeOptions.addArguments("--start-fullscreen");
    }
    chromeOptions.addArguments("--disable-notifications");
    chromeOptions.addArguments("--disable-gpu");
    chromeOptions.addArguments("--remote-allow-origins=*");
    driver= new ChromeDriver(chromeOptions);
    DriverFactory.setDriver(driver);
    driver.get(getData("url"));
  }


}
