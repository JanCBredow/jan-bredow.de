package bredow.jan.webserverdemo.util;

public enum ExitCode {
  CONFIGURATION_READ_ERROR(-5, "unable to read or access the config-file."),
  CONFIGURATION_WRITE_ERROR(-6, "unable to write or access the config-file.");

  int exitCode;
  String errorMessage;

  ExitCode(int exitCode, String errorMessage) {
    this.exitCode = exitCode;
    this.errorMessage = errorMessage;
  }

  public int exitCodeNumerical() {
    return exitCode;
  }

  public String errorMessage() {
    return errorMessage;
  }
}
