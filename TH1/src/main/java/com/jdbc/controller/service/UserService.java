package com.jdbc.controller.service;

import com.jdbc.controller.model.User;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserService implements IUserService {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcUserName = "root";
    private String jdbcPassword = "duytran12";

    private static final String INSERT_USERS_SQL = "INSERT INTO `users`" +
            "(`name`,`email`,`country`) VALUE" + "(?,?,?);";
    private static final String SELECT_USER_BY_ID = "SELECT `id`,`name`,`email`,`country` FROM `users` WHERE `id`=?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM `users`";
    private static final String DELETE_USERS_SQL = "DELETE FROM `users` WHERE `id` = ?";
    private static final String UPDATE_USERS_SQL = "UPDATE `users` SET `name` = ?, `email = ?`,`country = ?` WHERE `id` =?";

    public UserService() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, jdbcUserName, jdbcPassword);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.err.println("Không Có Driver  ");
        } catch (SQLException throwables) {
            // TODO Auto-generated catch block
            System.err.println("Không Kết Nối Được ");
        }
        System.err.println("Kết Nối Thành Công ");
        return connection;
    }

    @Override
    public void insertUser(User user) {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User selectUser(int id) {
        User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<User> users = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean deleteUser(int id) {
        boolean rowDeleted = false;
        try (Connection collection = getConnection();
             PreparedStatement statement = collection.prepareStatement(DELETE_USERS_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowDeleted;
    }

    @Override
    public boolean updateUser(User user) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setInt(4, user.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public User getUserById(int id) {
        User user = null;
        String query = "{CALL get_users_by_id(?)}";
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setInt(1, id);
            // Step 3: Execute the query or update query
            ResultSet rs = callableStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public void insertUsersStore(User user) {
        String query = "{CALL insert_user(?,?,?)}";
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(query);) {
            callableStatement.setString(1, user.getName());
            callableStatement.setString(2, user.getEmail());
            callableStatement.setString(3, user.getCountry());
            System.out.println(callableStatement);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
