package bredow.jan.webserverdemo.controller;

import bredow.jan.webserverdemo.inject.provider.HikariDataSourceProvider;
import bredow.jan.webserverdemo.log.ConsoleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@SuppressWarnings({"SqlResolve", "SqlNoDataSourceInspection"})
@Controller
public final class Access {
  private static final ConsoleLogger LOG = ConsoleLogger.of(Access.class);
  private final HikariDataSourceProvider dataSource;

  @Autowired
  private Access(HikariDataSourceProvider dataSource) {
    this.dataSource = dataSource;
  }

  /**
   * this is for counting, how many times my application has been viewed via QR-Code
   *
   * @return Thymeleaf Template name
   */
  @GetMapping("/")
  public String count() {
    countUp();
    return "index";
  }


  @GetMapping(value = "/robots.txt")
  public String robots() {
    return "robots.txt";
  }

  private int readCurrentCountFromDatabase() {
    var pool = dataSource.get();
    try (var connection = pool.getConnection()) {
      try (var statement = connection.prepareStatement("SELECT * FROM webserver_application")) {
        try (var result = statement.executeQuery()) {
          if (!result.next()) {
            return -1;
          }
          return result.getInt("visit_count");
        }
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
      return -1;
    }
  }

  private void countUp() {
    int newCount = readCurrentCountFromDatabase();
    var pool = dataSource.get();
    try (var connection = pool.getConnection()) {
      try (var statement =
             connection.prepareStatement("UPDATE webserver_application SET visit_count = ?")) {
        statement.setInt(1, newCount + 1);
        statement.executeUpdate();
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
}
