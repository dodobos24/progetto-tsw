package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventDao implements EventDaoInterface {

    @Override
    public void addEvent(EventBean event)  throws SQLException {
    	String sql = "INSERT INTO Events (event_name, event_date, venue, description, event_type, organizer, image, price, artist_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, event.getName());
            statement.setTimestamp(2, Timestamp.valueOf(event.getDate()));
            statement.setString(3, event.getVenue());
            statement.setString(4, event.getDescription());
            statement.setString(5, event.getEventType());
            statement.setString(6, event.getOrganizer());
            statement.setString(7, event.getImage());
            statement.setFloat(8, event.getPrice());
            statement.setInt(9, event.getArtistId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public EventBean getEventById(int id)  throws SQLException {
        String sql = "SELECT * FROM Events WHERE event_id = ?";
        EventBean event = null;
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("event_name");
                LocalDateTime date = resultSet.getTimestamp("event_date").toLocalDateTime();
                String venue = resultSet.getString("venue");
                String description = resultSet.getString("description");
                String eventType = resultSet.getString("event_type");
                String organizer = resultSet.getString("organizer");
                String image = resultSet.getString("image");
                float price = resultSet.getFloat("price");
                int artistId = resultSet.getInt("artist_id");
                event = new EventBean(id, name, date, venue, description, eventType, organizer, image, price, artistId);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public List<EventBean> getAllEvents()  throws SQLException {
        List<EventBean> events = new ArrayList<>();
        String sql = "SELECT * FROM Events";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                int id = resultSet.getInt("event_id");
                String name = resultSet.getString("event_name");
                LocalDateTime date = resultSet.getTimestamp("event_date").toLocalDateTime();
                String venue = resultSet.getString("venue");
                String description = resultSet.getString("description");
                String eventType = resultSet.getString("event_type");
                String organizer = resultSet.getString("organizer");
                String image = resultSet.getString("image");
                float price = resultSet.getFloat("price");
                int artistId = resultSet.getInt("artist_id");
                EventBean event = new EventBean(id, name, date, venue, description, eventType, organizer, image, price, artistId);
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public void updateEvent(EventBean event)  throws SQLException {
        String sql = "UPDATE Events SET event_name = ?, event_date = ?, venue = ?, description = ?, event_type = ?, organizer = ?, image = ?, artist_id = ? WHERE event_id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, event.getName());
            statement.setTimestamp(2, Timestamp.valueOf(event.getDate()));
            statement.setString(3, event.getVenue());
            statement.setString(4, event.getDescription());
            statement.setString(5, event.getEventType());
            statement.setString(6, event.getOrganizer());
            statement.setString(7,  event.getImage());
            statement.setFloat(8, event.getPrice());
            statement.setInt(9, event.getArtistId());
            statement.setInt(10, event.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEvent(int id)  throws SQLException {
    	String deleteEventSql = "DELETE FROM Events WHERE event_id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement deleteEventStatement = connection.prepareStatement(deleteEventSql)) {

            deleteEventStatement.setInt(1, id);
            int rowsAffected = deleteEventStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No event found with ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<EventBean> getEventsByArtist(int artistId)  throws SQLException {
        List<EventBean> events = new ArrayList<>();
        String sql = "SELECT * FROM Events WHERE artist_id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, artistId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("event_id");
                String name = resultSet.getString("event_name");
                LocalDateTime date = resultSet.getTimestamp("event_date").toLocalDateTime();
                String venue = resultSet.getString("venue");
                String description = resultSet.getString("description");
                String eventType = resultSet.getString("event_type");
                String organizer = resultSet.getString("organizer");
                String image = resultSet.getString("image");
                float price = resultSet.getFloat("price");
                EventBean event = new EventBean(id, name, date, venue, description, eventType, organizer, image, price, artistId);
                events.add(event);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public List<EventBean> getEventsByType(String type)  throws SQLException {
        List<EventBean> events = new ArrayList<>();
        String sql = "SELECT * FROM Events WHERE event_type = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, type);
            System.out.println("Executing query: " + statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("event_id");
                String name = resultSet.getString("event_name");
                LocalDateTime date = resultSet.getTimestamp("event_date").toLocalDateTime();
                String venue = resultSet.getString("venue");
                String description = resultSet.getString("description");
                String eventType = resultSet.getString("event_type");
                String organizer = resultSet.getString("organizer");
                String image = resultSet.getString("image");
                float price = resultSet.getFloat("price");
                int artistId = resultSet.getInt("artist_id");
                EventBean event = new EventBean(id, name, date, venue, description, eventType, organizer, image, price, artistId);
                events.add(event);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Number of events found: " + events.size());
        return events;
    }

    @Override
    public List<EventBean> getEventsByOrganizerId(int organizerId)  throws SQLException {
        List<EventBean> events = new ArrayList<>();
        String sql = "SELECT * FROM Events WHERE organizer = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, organizerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
                String venue = resultSet.getString("venue");
                String description = resultSet.getString("description");
                String eventType = resultSet.getString("event_type");
                String organizer = resultSet.getString("organizer");
                String image = resultSet.getString("image");
                float price = resultSet.getFloat("price");
                int artistId = resultSet.getInt("artist_id");
                EventBean event = new EventBean(id, name, date, venue, description, eventType, organizer, image, price, artistId);
                events.add(event);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
    
    @Override
    public List<EventBean> searchEvents(String venue, LocalDateTime date, String eventType, String artist) throws SQLException {
        List<EventBean> events = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Events WHERE 1=1");
        if (venue != null && !venue.trim().isEmpty()) sql.append(" AND venue = ?");
        if (date != null) sql.append(" AND event_date = ?");
        if (eventType != null && !eventType.trim().isEmpty()) sql.append(" AND event_type = ?");
        if (artist != null && !artist.trim().isEmpty()) sql.append(" AND artist_id = ?");

        System.out.println("SQL Query: " + sql.toString());

        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            
            int index = 1;
            if (venue != null && !venue.trim().isEmpty()) {
                statement.setString(index++, venue);
            }
            if (date != null) {
                statement.setTimestamp(index++, Timestamp.valueOf(date));
            }
            if (eventType != null && !eventType.trim().isEmpty()) {
                statement.setString(index++, eventType);
            }
            if (artist != null && !artist.trim().isEmpty()) {
                statement.setString(index++, artist);
            }

            System.out.println("Executing query: " + statement);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("event_id");
                String name = resultSet.getString("event_name");
                LocalDateTime resultDate = resultSet.getTimestamp("event_date").toLocalDateTime();
                String resultVenue = resultSet.getString("venue");
                String description = resultSet.getString("description");
                String resultEventType = resultSet.getString("event_type");
                String organizer = resultSet.getString("organizer");
                String image = resultSet.getString("image");
                float price = resultSet.getFloat("price");
                int artistId = resultSet.getInt("artist_id");
                EventBean event = new EventBean(id, name, resultDate, resultVenue, description, resultEventType, organizer, image, price, artistId);
                events.add(event);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
    
    @Override
    public List<EventBean> getTrendingEvents() throws SQLException {
        List<EventBean> events = new ArrayList<>();
        String sql = "SELECT event_id, event_name, event_date, venue, event_type, organizer, image, artist_id" +
        			 "FROM Events e" +
        			 "WHERE e.event_date = (" +
        		     "SELECT MIN(e2.event_date)" +
        		     "FROM Events e2" +
        		     "WHERE e2.event_type = e.event_type" +
        			 ");";

        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("event_id");
                String name = resultSet.getString("event_name");
                LocalDateTime date = resultSet.getTimestamp("event_date").toLocalDateTime();
                String venue = resultSet.getString("venue");
                String description = resultSet.getString("description");
                String eventType = resultSet.getString("event_type");
                String organizer = resultSet.getString("organizer");
                String image = resultSet.getString("image");
                float price = resultSet.getFloat("price");
                int artistId = resultSet.getInt("artist_id");

                EventBean event = new EventBean(id, name, date, venue, description, eventType, organizer, image, price, artistId);
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }


    private EventBean getEventByDateAndType(String eventType, LocalDateTime date) throws SQLException {
        String sql = "SELECT * FROM Events WHERE event_type = ? AND event_date = ? LIMIT 1";
        EventBean event = null;
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, eventType);
            statement.setTimestamp(2, Timestamp.valueOf(date));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("event_id");
                String name = resultSet.getString("event_name");
                String venue = resultSet.getString("venue");
                String description = resultSet.getString("description");
                String organizer = resultSet.getString("organizer");
                String image = resultSet.getString("image");
                float price = resultSet.getFloat("price");
                int artistId = resultSet.getInt("artist_id");
                event = new EventBean(id, name, date, venue, description, eventType, organizer, image, price, artistId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }
}


