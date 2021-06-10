package bredow.jan.webserverdemo.controller;

import bredow.jan.webserverdemo.inject.provider.HikariDataSourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@SuppressWarnings({"SqlResolve", "SqlNoDataSourceInspection"})
@Controller
public class CryTekController {
  private final HikariDataSourceProvider dataSource;

  @Autowired
  private CryTekController(HikariDataSourceProvider dataSource) {
    this.dataSource = dataSource;
  }

  @GetMapping("/hello-crytek")
  public String find(Model model) {
    countUp();
    var count = readFromDB();

    model.addAttribute("access_count", count);

    return "hello-crytek";
  }

  private int readFromDB() {
    var pool = dataSource.get();
    try (var connection = pool.getConnection()) {
      try (var statement = connection.prepareStatement("SELECT * FROM crytek_application")) {
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
    int newCount = readFromDB();
    var pool = dataSource.get();
    try (var connection = pool.getConnection()) {
      try (var statement =
          connection.prepareStatement("UPDATE crytek_application SET visit_count = ?")) {
        statement.setInt(1, newCount + 1);
        statement.executeUpdate();
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
}
