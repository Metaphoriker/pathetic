package de.metaphoriker.pathetic.engine.util;

import de.metaphoriker.pathetic.engine.Pathetic;
import org.tinylog.Logger;

public class ErrorLogger {

  private ErrorLogger() {
    throw new AssertionError("ErrorLogger is a utility class and should not be instantiated");
  }

  public static IllegalStateException logFatalError(String message) {
    return logFatalError(message, null);
  }

  public static IllegalStateException logFatalError(String message, Throwable cause) {
    Logger.error("===============================");
    Logger.error("A fatal error has occurred: {}", message);
    Logger.error("Please open an issue on the Pathetic GitHub page with all this information:");
    Logger.error("Version: {}", Pathetic.getEngineVersion());
    Logger.error("Java Version: {}", System.getProperty("java.version"));
    Logger.error("OS: {}", System.getProperty("os.name"));
    Logger.error("OS Architecture: {}", System.getProperty("os.arch"));
    Logger.error("===============================");
    return new IllegalStateException(message, cause);
  }

  public static IllegalStateException logFatalErrorWithStacktrace(String message, Throwable cause) {
    IllegalStateException exception = logFatalError(message, cause);
    Logger.error("Stacktrace:", cause);
    return exception;
  }
}
