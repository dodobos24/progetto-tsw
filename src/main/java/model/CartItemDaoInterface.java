package model;

import java.util.List;

public interface CartItemDaoInterface {
	void addCartItem(CartItemBean cartItem);
    CartItemBean getCartItemById(int cartItemId);
    List<CartItemBean> getCartItemsByCartId(int cartId);
    void updateCartItem(CartItemBean cartItem);
    void deleteCartItem(int cartItemId);
}
