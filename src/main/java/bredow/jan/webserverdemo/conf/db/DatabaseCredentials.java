package bredow.jan.webserverdemo.conf.db;

public final class DatabaseCredentials {
  private final String username;
  private final String password;

  private DatabaseCredentials(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public static DatabaseCredentials withCredentials(String username, String password) {
    return new DatabaseCredentials(username, password);
  }

  public String username() {
    return username;
  }

  public String password() {
    return password;
  }
}
