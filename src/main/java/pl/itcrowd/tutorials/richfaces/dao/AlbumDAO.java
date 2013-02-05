package pl.itcrowd.tutorials.richfaces.dao;

import pl.itcrowd.tutorials.richfaces.domain.Album;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlbumDAO {

    private final List<Album> albums;

    public AlbumDAO()
    {
        albums = new ArrayList<Album>();
    }

    public List<Album> getAllAlbums()
    {
        return Collections.unmodifiableList(albums);
    }
}
