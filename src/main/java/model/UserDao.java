package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserDaoInterface {

    @Override
    public void addUser(UserBean user) throws SQLException {
        String sql = "INSERT INTO Users (username, password, email, first_name, last_name, admin) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getNome());
            statement.setString(5, user.getCognome());
            statement.setBoolean(6, user.isAdmin());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error inserting user: " + e.getMessage());
        }
    }
    
    @Override
	public UserBean doRetrieve(String email, String password)  throws SQLException {
    	String sql = "SELECT * FROM Users WHERE email = ? AND password = ?";
        UserBean user = null;
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                user = new UserBean();
                user.setId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setNome(rs.getString("first_name"));
                user.setCognome(rs.getString("last_name"));
                user.setSaldo(rs.getFloat("saldo"));
                user.setAdmin(rs.getBoolean("admin"));
                user.setValid(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving user: " + e.getMessage());
        }
        return user;
	}

    @Override
    public UserBean getUserById(int id)  throws SQLException {
        String sql = "SELECT * FROM Users WHERE id = ?";
        UserBean user = null;
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                float saldo = resultSet.getFloat("saldo");
                boolean admin = resultSet.getBoolean("admin");
                user = new UserBean(id, username, password, email, name, surname, saldo, admin);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public UserBean getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM Users WHERE username = ?";
        UserBean user = null;
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                float saldo = resultSet.getFloat("saldo");
                boolean isAdmin = resultSet.getBoolean("admin");
                user = new UserBean(userId, username, password, email, firstName, lastName, saldo, isAdmin);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<UserBean> getAllUsers() throws SQLException {
        List<UserBean> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                float saldo = resultSet.getFloat("saldo");
                boolean admin = resultSet.getBoolean("admin");
                UserBean user = new UserBean(id, username, password, email, name, surname, saldo, admin);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void updateUser(UserBean user) throws SQLException {
        String sql = "UPDATE Users SET username = ?, password = ?, email = ?, first_name = ?, last_name = ?, saldo = ?, admin = ? WHERE user_id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            System.out.println("Updating user with ID: " + user.getId());
            System.out.println("New balance: " + user.getSaldo());

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getNome());
            statement.setString(5, user.getCognome());
            statement.setFloat(6, user.getSaldo());
            statement.setBoolean(7, user.isAdmin());
            statement.setInt(8, user.getId());

            int rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }


    @Override
    public void deleteUser(int id)  throws SQLException {
        String sql = "DELETE FROM Users WHERE id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

