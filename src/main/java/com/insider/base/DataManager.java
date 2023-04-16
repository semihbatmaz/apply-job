package com.insider.base;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import java.io.File;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;

public class DataManager {
  private static Object document;

  public static String getData(String key) {
    if (document == null) {
      String jsonString;
      try {
        jsonString = FileUtils.readFileToString(new File("src/test/resources/data.json"), StandardCharsets.UTF_8);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      document = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
    }
    return JsonPath.read(document, key);
  }

}