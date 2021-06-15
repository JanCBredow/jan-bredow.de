package bredow.jan.webserverdemo.conf.db;

public record DatabaseCredentials(String username, String password) {

/**
* Database Credential Wrapper
* @param username the Username
* @param password the Password
* @return Database Credential Wrapper
*/
  public static DatabaseCredentials withCredentials(String username, String password) {
    return new DatabaseCredentials(username, password);
  }
}
