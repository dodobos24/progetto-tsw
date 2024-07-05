package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserDaoInterface {

    @Override
    public void addUser(UserBean user) {
        String sql = "INSERT INTO Users (username, password, email, name, surname, admin) VALUES (?, ?, ?, ?, ?, ?)";
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
        }
    }
    
    @Override
	public UserBean doRetrieve(String username, String password) {
    	String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
    	UserBean user = null;
    	try (Connection connection = DatabaseUtility.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, username);
	        statement.setString(2, password);

	        ResultSet rs = statement.executeQuery();

	        if (rs.next()) {
	            user = new UserBean();
	            user.setUsername(rs.getString("username"));
	            user.setPassword(rs.getString("password"));
	        }
	    } catch (SQLException e) {
            e.printStackTrace();
        } 
	    return user;
	}

    @Override
    public UserBean getUserById(int id) {
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
                boolean admin = resultSet.getBoolean("admin");
                user = new UserBean(id, username, password, email, name, surname, admin);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<UserBean> getAllUsers() {
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
                boolean admin = resultSet.getBoolean("admin");
                UserBean user = new UserBean(id, username, password, email, name, surname, admin);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void updateUser(UserBean user) {
        String sql = "UPDATE Users SET username = ?, password = ?, email = ?, name = ?, surname = ?, admin = ? WHERE id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getNome());
            statement.setString(5, user.getCognome());
            statement.setBoolean(6, user.isAdmin());
            statement.setInt(7, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
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

