package pl.itcrowd.tutorials.richfaces.dao;

import pl.itcrowd.tutorials.richfaces.domain.Artist;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SessionScoped
@ManagedBean
public class ArtistDAO {

    private final List<Artist> artists;

    public ArtistDAO()
    {
        artists = Arrays.asList(new Artist(1L, "John"), new Artist(2L, "Tom"), new Artist(3L, "Celine"));
    }

    public List<Artist> getAllArtists()
    {
        return Collections.unmodifiableList(artists);
    }

    public Artist getEnsembleById(Long id){
        for(Artist a : artists){
            if(a.getId().equals(id)){
                return a;
            }
        }
        return null;
    }
}
