package model;

import java.util.List;

public interface UserDaoInterface {
	void addUser(UserBean user);
	UserBean doRetrieve(String username, String password);
    UserBean getUserById(int id);
    List<UserBean> getAllUsers();
    void updateUser(UserBean user);
    void deleteUser(int id);
}
