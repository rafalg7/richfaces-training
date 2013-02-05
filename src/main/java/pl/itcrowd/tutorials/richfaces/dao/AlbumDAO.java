package pl.itcrowd.tutorials.richfaces.dao;

import pl.itcrowd.tutorials.richfaces.domain.Album;
import pl.itcrowd.tutorials.richfaces.domain.AlbumTranslation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@ManagedBean
@SessionScoped
public class AlbumDAO {

    private final List<Album> albums;

    public AlbumDAO()
    {
        albums = new ArrayList<Album>();
        Album a = new Album();
        a.setId(4L);
        HashMap<String, AlbumTranslation> translations = new HashMap<String, AlbumTranslation>();
        translations.put("pol", new AlbumTranslation(a, 4L, "Pol", "Polish Title"));
        translations.put("eng", new AlbumTranslation(a, 4L, "Pol", "English Title"));
        a.setTranslations(translations);
    }

    public List<Album> getAllAlbums()
    {
        return Collections.unmodifiableList(albums);
    }

    public void saveAlbum(Album album){
        if(!albums.contains(album))
            albums.add(album);
    }

    public void deleteAlbum(Album album){
        if(albums.contains(album)){
            albums.remove(album);
        }
    }
}
