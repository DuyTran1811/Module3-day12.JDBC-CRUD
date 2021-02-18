package service;

import model.SmartPhone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SmartPhoneService implements ISmartPhoneService {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/dbSmartPhone";
    private static final String jdbcUserName = "root";
    private static final String jdbcPassword = "duytran12";

    private static final String SELECT_ALL_SMARTPHONE = "SELECT * FROM `smartphone`";
    private static final String CREATE_CREATE_SMARTPHONE = "INSERT INTO `smartphone`(`brand`,`name`,`price`,`year`,`sizescreen`)VALUES (?,?,?,?,?)";
    private static final String UPDATE_SMARTPHONE = "UPDATE `dbSmartPhone`.`smartPhone` SET `brand` = ?, `name` = ?, `price` = ?, `year` = ?, `sizeScreen` = ? WHERE (`id` = ?)";
    private static final String FILL_BY_ID = "CALL dbSmartPhone.get_by_id(?)";
    private static final String SEARCH_BY_NAME ="SELECT * FROM `smartPhone` where `name` like ?";
    private static final String DELETE_SMARTPHONE = "DELETE FROM `smartphone` WHERE `id`=?";

    public SmartPhoneService() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, jdbcUserName, jdbcPassword);
        } catch (ClassNotFoundException e) {
            System.err.println("Khong Co Driver");
        } catch (SQLException throwables) {
            System.err.println("Khong Ket Noi Duoc");
        }
        System.err.println("Ket Noi Thanh Cong");
        return connection;
    }

    @Override
    public List<SmartPhone> fillAll() {
        List<SmartPhone> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SMARTPHONE)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String brand = rs.getString(2);
                String name = rs.getString(3);
                float price = rs.getFloat(4);
                String year = rs.getString(5);
                String screen = rs.getString(6);
                list.add(new SmartPhone(id, brand, name, price, year, screen));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public void create(SmartPhone smartPhone) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(CREATE_CREATE_SMARTPHONE)) {
            ps.setString(1, smartPhone.getBrand());
            ps.setString(2, smartPhone.getName());
            ps.setFloat(3, smartPhone.getPrice());
            ps.setString(4, smartPhone.getYear());
            ps.setString(5, smartPhone.getSizescreen());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean update(SmartPhone smartPhone) {
        boolean rowUpdate = false;
        try (Connection connection = getConnection();
             PreparedStatement rs = connection.prepareStatement(UPDATE_SMARTPHONE)) {
            rs.setString(1, smartPhone.getBrand());
            rs.setString(2, smartPhone.getName());
            rs.setFloat(3, smartPhone.getPrice());
            rs.setString(4, smartPhone.getYear());
            rs.setString(5, smartPhone.getSizescreen());
            rs.setInt(6, smartPhone.getId());
            rowUpdate = rs.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdate;
    }

    @Override
    public boolean delete(int id) {
        boolean rowDelete = false;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_SMARTPHONE)) {
            ps.setInt(1, id);
            rowDelete = ps.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowDelete;
    }

    @Override
    public SmartPhone fillById(int id) {
        SmartPhone smartPhone = null;
        try (Connection connection = getConnection();
             CallableStatement cs = connection.prepareCall(FILL_BY_ID)) {
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                String brand = rs.getString(2);
                String name = rs.getString(3);
                float price = rs.getFloat(4);
                String year = rs.getString(5);
                String sizescreen = rs.getString(6);
                smartPhone = new SmartPhone(id, brand, name, price, year, sizescreen);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return smartPhone;
    }

    @Override
    public List<SmartPhone> searchByIdName(String name) {
        List<SmartPhone> list = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SEARCH_BY_NAME)) {
            ps.setString(1,"%"+name+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                String brand = rs.getString(2);
                String names = rs.getString(3);
                float price = rs.getFloat(4);
                String year = rs.getString(5);
                String sizescreen = rs.getString(6);
                list.add(new SmartPhone(id,brand,names,price,year,sizescreen));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
