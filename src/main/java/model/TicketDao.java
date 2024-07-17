package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketDao implements TicketDaoInterface {

    @Override
    public void addTicket(TicketBean ticket)  throws SQLException {
        String sql = "INSERT INTO Tickets (event_id, user_id, purchase_date, seat_number, price) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, ticket.getEventId());
            statement.setInt(2, ticket.getUserId());
            statement.setTimestamp(3, Timestamp.valueOf(ticket.getPurchaseDate()));
            statement.setString(4, ticket.getSeatNumber());
            statement.setFloat(5, ticket.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TicketBean getTicketById(int id)  throws SQLException {
        String sql = "SELECT * FROM Tickets WHERE id = ?";
        TicketBean ticket = null;
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int eventId = resultSet.getInt("event_id");
                int userId = resultSet.getInt("user_id");
                LocalDateTime purchaseDate = resultSet.getTimestamp("purchase_date").toLocalDateTime();
                String seatNumber = resultSet.getString("seat_number");
                float price = resultSet.getFloat("price");
                ticket = new TicketBean(id, eventId, userId, purchaseDate, seatNumber, price);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public List<TicketBean> getAllTickets()  throws SQLException {
        List<TicketBean> tickets = new ArrayList<>();
        String sql = "SELECT * FROM Tickets";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int eventId = resultSet.getInt("event_id");
                int userId = resultSet.getInt("user_id");
                LocalDateTime purchaseDate = resultSet.getTimestamp("purchase_date").toLocalDateTime();
                String seatNumber = resultSet.getString("seat_number");
                float price = resultSet.getFloat("price");
                TicketBean ticket = new TicketBean(id, eventId, userId, purchaseDate, seatNumber, price);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public void updateTicket(TicketBean ticket)  throws SQLException {
        String sql = "UPDATE Tickets SET event_id = ?, user_id = ?, purchase_date = ?, seat_number = ?, price = ? WHERE id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, ticket.getEventId());
            statement.setInt(2, ticket.getUserId());
            statement.setTimestamp(3, Timestamp.valueOf(ticket.getPurchaseDate()));
            statement.setString(4, ticket.getSeatNumber());
            statement.setFloat(5, ticket.getPrice());
            statement.setInt(6, ticket.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTicket(int id)  throws SQLException {
        String sql = "DELETE FROM Tickets WHERE id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TicketBean> getTicketByUser(int userId)  throws SQLException {
        List<TicketBean> tickets = new ArrayList<>();
        String sql = "SELECT * FROM Tickets WHERE user_id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int eventId = resultSet.getInt("event_id");
                LocalDateTime purchaseDate = resultSet.getTimestamp("purchase_date").toLocalDateTime();
                String seatNumber = resultSet.getString("seat_number");
                float price = resultSet.getFloat("price");
                TicketBean ticket = new TicketBean(id, eventId, userId, purchaseDate, seatNumber, price);
                tickets.add(ticket);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public List<TicketBean> getTicketByEvent(int eventId)  throws SQLException {
        List<TicketBean> tickets = new ArrayList<>();
        String sql = "SELECT * FROM Tickets WHERE event_id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, eventId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                LocalDateTime purchaseDate = resultSet.getTimestamp("purchase_date").toLocalDateTime();
                String seatNumber = resultSet.getString("seat_number");
                float price = resultSet.getFloat("price");
                TicketBean ticket = new TicketBean(id, eventId, userId, purchaseDate, seatNumber, price);
                tickets.add(ticket);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}

