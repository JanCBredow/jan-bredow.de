package bredow.jan.webserverdemo.log;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class ConsoleLogger {
  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_RED = "\u001B[31m";
  private static final String ANSI_GREEN = "\u001B[32m";
  private static final String ANSI_YELLOW = "\u001B[33m";
  private static final String ANSI_WHITE = "\u001B[37m";
  private static final String CONSOLE_LOG_SPACER = "    ";
  private final Class<?> invokerClass;

  private ConsoleLogger(Class<?> invokerClass) {
    this.invokerClass = invokerClass;
  }

  public static ConsoleLogger of(Class<?> clazz) {
    return new ConsoleLogger(clazz);
  }

  public ConsoleLogger appendLogger(ConsoleLogger invoker) {
    return this;
  }

  public ConsoleLogger logSpaced(String message, int spacesBeforeText) {
    System.out.println(
        ANSI_GREEN
            + resolveTime()
            + " | "
            + invokerClass.getSimpleName()
            + " - INFO"
            + ANSI_RESET
            + ": "
            + " ".repeat(Math.max(0, spacesBeforeText))
            + message);
    return this;
  }

  public ConsoleLogger log(String message) {
    System.out.println(
        ANSI_GREEN
            + resolveTime()
            + " | "
            + invokerClass.getSimpleName()
            + " - INFO"
            + ANSI_RESET
            + ": "
            + message);
    return this;
  }

  public ConsoleLogger info(String message) {
    log(message);
    return this;
  }

  public ConsoleLogger space() {
    space(0);
    return this;
  }

  public ConsoleLogger warn(String warning) {
    System.out.println(
        ANSI_YELLOW
            + resolveTime()
            + " | "
            + invokerClass.getName()
            + " - WARN"
            + ANSI_RESET
            + ": "
            + warning);
    space();
    return this;
  }

  public ConsoleLogger error(String error) {
    System.out.println(
        ANSI_RED
            + resolveTime()
            + " | "
            + invokerClass.getCanonicalName()
            + " - ERROR"
            + ANSI_RESET
            + ": "
            + error);

    return this;
  }

  public ConsoleLogger errorIf(String error, boolean condition) {
    if (condition) {
      System.out.println(
          ANSI_RED
              + resolveTime()
              + " | "
              + invokerClass.getCanonicalName()
              + " - ERROR"
              + ANSI_RESET
              + ": "
              + error);
    }
    return this;
  }

  public ConsoleLogger space(int colons) {
    for (int i = 0; i < colons; i++) {
      System.out.println("\n");
    }
    return this;
  }

  private String resolveTime() {
    return new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss")
        .format(new Date(System.currentTimeMillis()));
  }
}
