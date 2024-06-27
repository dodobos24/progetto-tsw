package model;

import java.util.List;

public interface UserDaoInterface {
	void addUser(UserBean user);
    UserBean getUserById(int id);
    List<UserBean> getAllUsers();
    void updateUser(UserBean user);
    void deleteUser(int id);
}
