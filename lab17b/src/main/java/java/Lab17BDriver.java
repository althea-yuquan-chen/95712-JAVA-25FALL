package java;
import java.sql.*;

public class Lab17BDriver {
    public static void main(String[] args) {

        String url = "jdbc:sqlite:dogsdb.sqlite3";
        try (Connection connection = DriverManager.getConnection(url)) {
            System.out.println("all good");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from dogs");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }

            // Create the breed table and insert some data here
            // Then display it all

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
