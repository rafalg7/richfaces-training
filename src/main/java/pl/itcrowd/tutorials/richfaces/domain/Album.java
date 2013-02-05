package pl.itcrowd.tutorials.richfaces.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Album implements Serializable {

    private Artist artist;

    private Ensemble ensemble;

    private Long id;

    private Set<Track> tracks = new HashSet<Track>();

    private Map<String, AlbumTranslation> translations = new HashMap<String, AlbumTranslation>();

    public Album()
    {
        this.id = Calendar.getInstance().getTimeInMillis();
    }

    public Artist getArtist()
    {
        return artist;
    }

    public void setArtist(Artist artist)
    {
        if(null!=artist){
            ensemble=null;
        }
        this.artist = artist;
    }

    public Ensemble getEnsemble()
    {
        return ensemble;
    }

    public String getArtistNameOrEnsembleName(){
        if(null==artist){
            return ensemble==null ? null : ensemble.getName();
        }else{
            return artist.getName();
        }
    }

    public void setEnsemble(Ensemble ensemble)
    {
        if(null!=ensemble){
            artist=null;
        }
        this.ensemble = ensemble;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Set<Track> getTracks()
    {
        return tracks;
    }

    public void setTracks(Set<Track> tracks)
    {
        this.tracks = tracks;
    }

    public Map<String, AlbumTranslation> getTranslations()
    {
        return translations;
    }

    public void setTranslations(Map<String, AlbumTranslation> translations)
    {
        this.translations = translations;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Album)) {
            return false;
        }

        Album album = (Album) o;

        return !(getId() == null || !getId().equals(album.getId()));
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }
}
