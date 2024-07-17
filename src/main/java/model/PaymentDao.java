package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao implements PaymentDaoInterface {

    @Override
    public void addPayment(PaymentBean payment)  throws SQLException {
        String sql = "INSERT INTO Payments (user_id, ticket_id, amount, payment_date, payment_method) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, payment.getUserId());
            statement.setInt(2, payment.getTicketId());
            statement.setFloat(3, payment.getAmount());
            statement.setTimestamp(4, Timestamp.valueOf(payment.getPaymentDate()));
            statement.setString(5, payment.getPaymentMethod());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PaymentBean getPaymentById(int id)  throws SQLException {
        String sql = "SELECT * FROM Payments WHERE id = ?";
        PaymentBean payment = null;
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                int ticketId = resultSet.getInt("ticket_id");
                float amount = resultSet.getFloat("amount");
                LocalDateTime paymentDate = resultSet.getTimestamp("payment_date").toLocalDateTime();
                String paymentMethod = resultSet.getString("payment_method");
                payment = new PaymentBean(id, userId, ticketId, amount, paymentDate, paymentMethod);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }

    @Override
    public List<PaymentBean> getAllPayments()  throws SQLException {
        List<PaymentBean> payments = new ArrayList<>();
        String sql = "SELECT * FROM Payments";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                int ticketId = resultSet.getInt("ticket_id");
                float amount = resultSet.getFloat("amount");
                LocalDateTime paymentDate = resultSet.getTimestamp("payment_date").toLocalDateTime();
                String paymentMethod = resultSet.getString("payment_method");
                PaymentBean payment = new PaymentBean(id, userId, ticketId, amount, paymentDate, paymentMethod);
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public void updatePayment(PaymentBean payment)  throws SQLException {
        String sql = "UPDATE Payments SET user_id = ?, ticket_id = ?, amount = ?, payment_date = ?, payment_method = ? WHERE id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, payment.getUserId());
            statement.setInt(2, payment.getTicketId());
            statement.setFloat(3, payment.getAmount());
            statement.setTimestamp(4, Timestamp.valueOf(payment.getPaymentDate()));
            statement.setString(5, payment.getPaymentMethod());
            statement.setInt(6, payment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePayment(int id)  throws SQLException {
        String sql = "DELETE FROM Payments WHERE id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PaymentBean> getPaymentByUser(int userId)  throws SQLException {
        List<PaymentBean> payments = new ArrayList<>();
        String sql = "SELECT * FROM Payments WHERE user_id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int ticketId = resultSet.getInt("ticket_id");
                float amount = resultSet.getFloat("amount");
                LocalDateTime paymentDate = resultSet.getTimestamp("payment_date").toLocalDateTime();
                String paymentMethod = resultSet.getString("payment_method");
                PaymentBean payment = new PaymentBean(id, userId, ticketId, amount, paymentDate, paymentMethod);
                payments.add(payment);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public List<PaymentBean> getPaymentByTicket(int ticketId)  throws SQLException {
        List<PaymentBean> payments = new ArrayList<>();
        String sql = "SELECT * FROM Payments WHERE ticket_id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, ticketId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                float amount = resultSet.getFloat("amount");
                LocalDateTime paymentDate = resultSet.getTimestamp("payment_date").toLocalDateTime();
                String paymentMethod = resultSet.getString("payment_method");
                PaymentBean payment = new PaymentBean(id, userId, ticketId, amount, paymentDate, paymentMethod);
                payments.add(payment);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }
}

