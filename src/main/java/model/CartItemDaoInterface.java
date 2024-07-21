package model;

import java.sql.SQLException;
import java.util.List;

public interface CartItemDaoInterface {
	void addCartItem(CartItemBean cartItem) throws SQLException;
    CartItemBean getCartItemById(int cartItemId) throws SQLException;
    List<CartItemBean> getCartItemsByCartId(int cartId) throws SQLException;
    void deleteCartItemByEventId(int eventId) throws SQLException;
    void updateCartItem(CartItemBean cartItem) throws SQLException;
    void deleteCartItem(int cartItemId) throws SQLException;
    void addOrUpdateCartItem(CartItemBean item) throws SQLException;
}
