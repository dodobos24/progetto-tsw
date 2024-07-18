package model;

import java.sql.SQLException;
import java.util.List;

public interface ArtistDaoInterface {
    void addArtist(ArtistBean artist) throws SQLException;
    ArtistBean getArtistById(int id) throws SQLException;
    List<ArtistBean> getAllArtists() throws SQLException;
    void updateArtist(ArtistBean artist) throws SQLException;
    void deleteArtist(int id) throws SQLException;
}
