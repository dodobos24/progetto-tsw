package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistDao implements ArtistDaoInterface {

    @Override
    public void addArtist(ArtistBean artist) {
        String sql = "INSERT INTO Artists (name, genre) VALUES (?, ?)";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, artist.getName());
            statement.setString(2, artist.getGenre());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArtistBean getArtistById(int id) {
        String sql = "SELECT * FROM Artists WHERE id = ?";
        ArtistBean artist = null;
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String genre = resultSet.getString("genre");
                artist = new ArtistBean(id, name, genre);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artist;
    }

    @Override
    public List<ArtistBean> getAllArtists() {
        List<ArtistBean> artists = new ArrayList<>();
        String sql = "SELECT * FROM Artists";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String genre = resultSet.getString("genre");
                ArtistBean artist = new ArtistBean(id, name, genre);
                artists.add(artist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artists;
    }

    @Override
    public void updateArtist(ArtistBean artist) {
        String sql = "UPDATE Artists SET name = ?, genre = ? WHERE id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, artist.getName());
            statement.setString(2, artist.getGenre());
            statement.setInt(3, artist.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteArtist(int id) {
        String sql = "DELETE FROM Artists WHERE id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
