package model;

import java.util.List;

public interface CartDaoInterface {
	void addCart(CartBean cart);
    CartBean getCartById(int cartId);
    CartBean getCartByUserId(int userId);
    void updateCart(CartBean cart);
    void deleteCart(int cartId);
    void addItemToCart(int cartId, CartItemBean item);
    void updateCartItem(int cartId, CartItemBean item);
    void removeItemFromCart(int cartId, int itemId);
    List<CartItemBean> getItemsByCartId(int cartId);
}
