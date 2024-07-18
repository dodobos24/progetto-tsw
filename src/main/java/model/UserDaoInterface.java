package model;

import java.sql.SQLException;
import java.util.List;

public interface UserDaoInterface {
	void addUser(UserBean user) throws SQLException;
	UserBean doRetrieve(String username, String password) throws SQLException;
    UserBean getUserById(int id) throws SQLException;
    List<UserBean> getAllUsers() throws SQLException;
    void updateUser(UserBean user) throws SQLException;
    void deleteUser(int id) throws SQLException;
}
