package model;

import java.sql.SQLException;
import java.util.List;

public interface CartDaoInterface {
	void addCart(CartBean cart) throws SQLException;
    CartBean getCartById(int cartId) throws SQLException;
    CartBean getCartByUserId(int userId) throws SQLException;
    void updateCart(CartBean cart) throws SQLException;
    void deleteCart(int cartId) throws SQLException;
    void addItemToCart(int cartId, CartItemBean item) throws SQLException;
    void updateCartItem(int cartId, CartItemBean item) throws SQLException;
    void removeItemFromCart(int cartId, int itemId) throws SQLException;
    List<CartItemBean> getItemsByCartId(int cartId) throws SQLException;
}
