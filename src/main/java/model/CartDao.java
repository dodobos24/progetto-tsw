package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CartDao implements CartDaoInterface {

    @Override
    public void addCart(CartBean cart) {
        String sql = "INSERT INTO Carts (user_id, created_at) VALUES (?, ?)";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, cart.getUserId());
            statement.setTimestamp(2, Timestamp.valueOf(cart.getCreatedAt()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CartBean getCartById(int cartId) {
        String sql = "SELECT * FROM Carts WHERE id = ?";
        CartBean cart = null;
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, cartId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                cart = new CartBean(id, userId, createdAt);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }

    @Override
    public CartBean getCartByUserId(int userId) {
        String sql = "SELECT * FROM Carts WHERE user_id = ?";
        CartBean cart = null;
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                cart = new CartBean(id, userId, createdAt);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }

    @Override
    public void updateCart(CartBean cart) {
        String sql = "UPDATE Carts SET user_id = ?, created_at = ? WHERE id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, cart.getUserId());
            statement.setTimestamp(2, Timestamp.valueOf(cart.getCreatedAt()));
            statement.setInt(3, cart.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCart(int cartId) {
        String sql = "DELETE FROM Carts WHERE id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, cartId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addItemToCart(int cartId, CartItemBean item) {
        String sql = "INSERT INTO CartItems (cart_id, event_id, quantity) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, cartId);
            statement.setInt(2, item.getEventId());
            statement.setInt(3, item.getQuantity());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCartItem(int cartId, CartItemBean item) {
        String sql = "UPDATE CartItems SET event_id = ?, quantity = ? WHERE cart_id = ? AND item_id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, item.getEventId());
            statement.setInt(2, item.getQuantity());
            statement.setInt(3, cartId);
            statement.setInt(4, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeItemFromCart(int cartId, int itemId) {
        String sql = "DELETE FROM CartItems WHERE cart_id = ? AND item_id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, cartId);
            statement.setInt(2, itemId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CartItemBean> getItemsByCartId(int cartId) {
        List<CartItemBean> items = new ArrayList<>();
        String sql = "SELECT * FROM CartItems WHERE cart_id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, cartId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int itemId = resultSet.getInt("item_id");
                int eventId = resultSet.getInt("event_id");
                int quantity = resultSet.getInt("quantity");
                String seatNumber = resultSet.getString("seat_number");
	            float price = resultSet.getFloat("price");
                CartItemBean item = new CartItemBean(itemId, cartId, eventId, quantity, seatNumber, price);
                items.add(item);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}

