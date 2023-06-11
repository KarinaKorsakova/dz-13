package utils;

import models.Developers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBReader {

    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";

    private final static String USER_NAME = "postgres";

    private final static String USER_PASSWORD = "postgres";

    private final static String QUERY_SELECT_ALL = "select * from developers";
    private final static String QUERY_INSERT = "insert into developers values(?, ?, ?, ?)";
    private final static String QUERY_UPDATE = "update developers set salary=? where id=?";
    private final static String QUERY_DELETE = "delete from developers where id=?";


    public static List<Developers> getDevelopersFromDB() {
        List<Developers> developers = new ArrayList<>();


        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            Statement sqlStatement = connection.createStatement();
            try (ResultSet resultSet = sqlStatement.executeQuery(QUERY_SELECT_ALL)) {


                while (resultSet.next()) {
                    Developers developer = new Developers(resultSet.getString("name"),
                            resultSet.getString("position"),
                            resultSet.getInt("salary"));

                    developers.add(developer);

                }
            }


        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string. URL [%s], name [%s], pass [%s]",
                    URL, USER_NAME, USER_PASSWORD));
        }
        return developers;
    }

    public static void insert(int id, String name, String position, int salary) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, position);
            preparedStatement.setInt(4, salary);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string. URL [%s], name [%s], pass [%s]",
                    URL, USER_NAME, USER_PASSWORD));
        }
    }


    public static Developers update(int id, int salary) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, salary);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string. URL [%s], name [%s], pass [%s]",
                    URL, USER_NAME, USER_PASSWORD));
        }
        return developers;
    }

    public static Developers delete(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string. URL [%s], name [%s], pass [%s]",
                    URL, USER_NAME, USER_PASSWORD));
        }
        return developers;
    }

    public static void main(String[] args) {
        getDevelopersFromDB();


    }

}