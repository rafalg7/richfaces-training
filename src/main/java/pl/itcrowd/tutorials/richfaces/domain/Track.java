package pl.itcrowd.tutorials.richfaces.domain;

import java.io.Serializable;
import java.util.Calendar;

public class Track implements Serializable {

    private Album album;

    private Long id;

    private int position;

    private Recording recording;

    public Track()
    {

    }

    public Album getAlbum()
    {
        return album;
    }

    public void setAlbum(Album album)
    {
        this.album = album;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public int getPosition()
    {
        return position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public Recording getRecording()
    {
        return recording;
    }

    public void setRecording(Recording recording)
    {
        this.recording = recording;
    }

    @Override
    public boolean equals(Object o)
    {

        if (this == o) {
            return true;
        }
        if (!(o instanceof Track)) {
            return false;
        }

        Track track = (Track) o;

        return !(getId() == null || !getId().equals(track.getId()));
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }
}
