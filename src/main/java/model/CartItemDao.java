package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDao implements CartItemDaoInterface {
	@Override
	public void addCartItem(CartItemBean cartItem) {
	    String sql = "INSERT INTO CartItems (cart_id, event_id, quantity) VALUES (?, ?, ?)";
	    try (Connection connection = DatabaseUtility.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	
	        statement.setInt(1, cartItem.getCartId());
	        statement.setInt(2, cartItem.getEventId());
	        statement.setInt(3, cartItem.getQuantity());
	
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public CartItemBean getCartItemById(int cartItemId) {
	    String sql = "SELECT * FROM CartItems WHERE item_id = ?";
	    CartItemBean cartItem = null;
	    try (Connection connection = DatabaseUtility.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	
	        statement.setInt(1, cartItemId);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            int cartId = resultSet.getInt("cart_id");
	            int eventId = resultSet.getInt("event_id");
	            int quantity = resultSet.getInt("quantity");
	            String seatNumber = resultSet.getString("seat_number");
	            float price = resultSet.getFloat("price");
	            cartItem = new CartItemBean(cartItemId, cartId, eventId, quantity, seatNumber, price);
	        }
	        resultSet.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cartItem;
	}
	
	@Override
	public List<CartItemBean> getCartItemsByCartId(int cartId) {
	    List<CartItemBean> listCartItems = new ArrayList<>();
	    String sql = "SELECT * FROM CartItems WHERE cart_id = ?";
	    try (Connection connection = DatabaseUtility.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	
	        statement.setInt(1, cartId);
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            int cartItemId = resultSet.getInt("item_id");
	            int eventId = resultSet.getInt("event_id");
	            int quantity = resultSet.getInt("quantity");
	            String seatNumber = resultSet.getString("seat_number");
	            float price = resultSet.getFloat("price");
	            CartItemBean cartItem = new CartItemBean(cartItemId, cartId, eventId, quantity, seatNumber, price);
	            listCartItems.add(cartItem);
	        }
	        resultSet.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return listCartItems;
	}
	
	@Override
	public void updateCartItem(CartItemBean cartItem) {
	    String sql = "UPDATE CartItems SET quantity = ? WHERE item_id = ?";
	    try (Connection connection = DatabaseUtility.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	
	        statement.setInt(1, cartItem.getQuantity());
	        statement.setInt(2, cartItem.getId());
	
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void deleteCartItem(int cartItemId) {
	    String sql = "DELETE FROM CartItems WHERE item_id = ?";
	    try (Connection connection = DatabaseUtility.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	
	        statement.setInt(1, cartItemId);
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
