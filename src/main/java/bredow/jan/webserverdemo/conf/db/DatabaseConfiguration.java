package bredow.jan.webserverdemo.conf.db;

public final class DatabaseConfiguration {

  private final String databaseHost;
  private final int databasePort;
  private final String databaseName;

  private final DatabaseCredentials databaseCredentials;

  private DatabaseConfiguration(
      String databaseHost,
      int databasePort,
      String databaseName,
      DatabaseCredentials databaseCredentials) {
    this.databaseHost = databaseHost;
    this.databasePort = databasePort;
    this.databaseName = databaseName;
    this.databaseCredentials = databaseCredentials;
  }

  public static DatabaseConfiguration create(
      String databaseHost,
      int databasePort,
      String databaseName,
      DatabaseCredentials databaseCredentials) {
    return new DatabaseConfiguration(databaseHost, databasePort, databaseName, databaseCredentials);
  }

  public String databaseHost() {
    return databaseHost;
  }

  public int databasePort() {
    return databasePort;
  }

  public String databaseName() {
    return databaseName;
  }

  public DatabaseCredentials databaseCredentials() {
    return databaseCredentials;
  }
}
