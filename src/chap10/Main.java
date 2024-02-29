package chap10;

import data.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

  public static void main(String[] args) {
    try (Connection connection = JdbcHelper.getConnection()) {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks");
      resultSet.next();
      while (resultSet.next()) {
        String title = resultSet.getString(2);
        Date date = resultSet.getDate(4);
        System.out.println(title + " " + date.toLocalDate());
      }
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
