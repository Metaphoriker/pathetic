package de.metaphoriker.pathetic;

import de.metaphoriker.pathetic.util.ErrorLogger;
import lombok.Getter;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class Pathetic {

  private static final String PROPERTIES_FILE = "pathetic.properties";

  @Getter
  private static String engineVersion;

  public static void loadEngineVersion() {
    try (InputStream inputStream =
           Pathetic.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
      Properties properties = new Properties();
      properties.load(inputStream);

      engineVersion = properties.getProperty("engine.version");
    } catch (IOException e) {
      throw ErrorLogger.logFatalError("Error loading engine version", e);
    }
  }
}
