package bredow.jan.webserverdemo.conf;

import bredow.jan.webserverdemo.conf.db.DatabaseConfiguration;
import bredow.jan.webserverdemo.conf.db.DatabasePoolConfiguration;

public record Configuration(DatabaseConfiguration databaseConfiguration,
                            DatabasePoolConfiguration databasePoolConfiguration) {

  public static Configuration createWith(
          DatabaseConfiguration initialDatabaseConfiguration,
          DatabasePoolConfiguration databasePoolConfiguration) {
    return new Configuration(initialDatabaseConfiguration, databasePoolConfiguration);
  }
}
