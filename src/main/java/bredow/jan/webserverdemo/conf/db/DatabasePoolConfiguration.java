package bredow.jan.webserverdemo.conf.db;


/**
 * This class is in Usage only in usage to
 * create a Default Config and parse it via GSON
 */


@SuppressWarnings("FieldCanBeLocal")
public final class DatabasePoolConfiguration {

  private final int maximumPoolSize = 5;
  private final int minimumIdle = 3;

  // Database Cache and Behavior Configuration, be carefully with changes here
  private final boolean cachePrepStmts = true;
  private final int prepStmtCacheSize = 250;
  private final int prepStmtCacheSqlLimit = 2048;
  private final boolean useServerPrepStmts = true;
  private final boolean useLocalSessionState = true;
  private final boolean rewriteBatchedStatements = true;
  private final boolean cacheResultSetMetadata = true;
  private final boolean cacheServerConfiguration = true;
  private final boolean elideSetAutoCommits = true;
  private final boolean maintainTimeStats = true;
  private final String ApplicationName = "application-name";

  public static DatabasePoolConfiguration empty() {
    return new DatabasePoolConfiguration();
  }

  public int maximumPoolSize() {
    return maximumPoolSize;
  }

  public int minimumIdle() {
    return minimumIdle;
  }

  public boolean cachePrepStmts() {
    return cachePrepStmts;
  }

  public int prepStmtCacheSize() {
    return prepStmtCacheSize;
  }

  public int prepStmtCacheSqlLimit() {
    return prepStmtCacheSqlLimit;
  }

  public boolean useServerPrepStmts() {
    return useServerPrepStmts;
  }

  public boolean useLocalSessionState() {
    return useLocalSessionState;
  }

  public boolean rewriteBatchedStatements() {
    return rewriteBatchedStatements;
  }

  public boolean cacheResultSetMetadata() {
    return cacheResultSetMetadata;
  }

  public boolean cacheServerConfiguration() {
    return cacheServerConfiguration;
  }

  public boolean elideSetAutoCommits() {
    return elideSetAutoCommits;
  }

  public boolean maintainTimeStats() {
    return maintainTimeStats;
  }

  public String applicationName() {
    return ApplicationName;
  }
}
