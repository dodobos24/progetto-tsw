package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDao implements CartItemDaoInterface {

    @Override
    public void addCartItem(CartItemBean cartItem) throws SQLException {
        String query = "INSERT INTO cartitems (cart_id, event_id, quantity) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cartItem.getCartId());
            statement.setInt(2, cartItem.getEventId());
            statement.setInt(3, cartItem.getQuantity());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteCartItem(int cartItemId) throws SQLException {
        String query = "DELETE FROM cartitems WHERE cart_item_id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cartItemId);
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteCartItemByEventId(int eventId) throws SQLException {
        String query = "DELETE FROM cartitems WHERE event_id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, eventId);
            statement.executeUpdate();
        }
    }

    @Override
    public void updateCartItem(CartItemBean cartItem) throws SQLException {
        String query = "UPDATE cartitems SET quantity = ? WHERE cart_item_id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cartItem.getQuantity());
            statement.setInt(2, cartItem.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public CartItemBean getCartItemById(int cartItemId) throws SQLException {
        String query = "SELECT * FROM cartitems WHERE cart_item_id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cartItemId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    CartItemBean cartItem = new CartItemBean();
                    cartItem.setId(resultSet.getInt("cart_item_id"));
                    cartItem.setCartId(resultSet.getInt("cart_id"));
                    cartItem.setEventId(resultSet.getInt("event_id"));
                    cartItem.setQuantity(resultSet.getInt("quantity"));
                    return cartItem;
                }
            }
        }
        return null;
    }

    @Override
	public List<CartItemBean> getCartItemsByCartId(int cartId)  throws SQLException {
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
	            CartItemBean cartItem = new CartItemBean(cartItemId, cartId, eventId, quantity);
	            listCartItems.add(cartItem);
	        }
	        resultSet.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return listCartItems;
	}
    
    @Override
    public void addOrUpdateCartItem(CartItemBean item) throws SQLException {
        String checkQuery = "SELECT quantity FROM CartItems WHERE cart_id = ? AND event_id = ?";
        String updateQuery = "UPDATE CartItems SET quantity = quantity + ? WHERE cart_id = ? AND event_id = ?";
        String insertQuery = "INSERT INTO CartItems (cart_id, event_id, quantity) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseUtility.getConnection()) {
            // Check if the item already exists
            try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, item.getCartId());
                checkStmt.setInt(2, item.getEventId());
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        // Item exists, update quantity
                        try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                            updateStmt.setInt(1, item.getQuantity());
                            updateStmt.setInt(2, item.getCartId());
                            updateStmt.setInt(3, item.getEventId());
                            updateStmt.executeUpdate();
                            System.out.println("Updated cart item: " + item);
                        }
                    } else {
                        // Item does not exist, insert new
                        try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                            insertStmt.setInt(1, item.getCartId());
                            insertStmt.setInt(2, item.getEventId());
                            insertStmt.setInt(3, item.getQuantity());
                            insertStmt.executeUpdate();
                            System.out.println("Inserted new cart item: " + item);
                        }
                    }
                }
            }
        }
    }
}
