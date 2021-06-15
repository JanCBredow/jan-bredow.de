package bredow.jan.webserverdemo.conf.db;

public record DatabaseConfiguration(String databaseHost, int databasePort, String databaseName,
                                    DatabaseCredentials databaseCredentials) {

/**
*
* @param databaseHost the hostadress of the database
* @param databasePort database port, default 5432
* @param databaseName database name
* @param databaseCredentials databasecredentials wrapper
* @return DatabaseConfiguration Wrapper
*/
  public static DatabaseConfiguration create(
          String databaseHost,
          int databasePort,
          String databaseName,
          DatabaseCredentials databaseCredentials) {
    return new DatabaseConfiguration(databaseHost, databasePort, databaseName, databaseCredentials);
  }
}
