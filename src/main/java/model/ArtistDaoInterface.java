package model;

import java.util.List;

public interface ArtistDaoInterface {
    void addArtist(ArtistBean artist);
    ArtistBean getArtistById(int id);
    List<ArtistBean> getAllArtists();
    void updateArtist(ArtistBean artist);
    void deleteArtist(int id);
}
