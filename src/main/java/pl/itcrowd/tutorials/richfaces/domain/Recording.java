package pl.itcrowd.tutorials.richfaces.domain;

public class Recording {

    private Artist artist;

    private Long id;

    private String title;

    public Recording()
    {
    }

    public Recording(Artist artist, Long id, String title)
    {
        this.artist = artist;
        this.id = id;
        this.title = title;
    }

    public Artist getArtist()
    {
        return artist;
    }

    public void setArtist(Artist artist)
    {
        this.artist = artist;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Recording)) {
            return false;
        }

        Recording recording = (Recording) o;

        return !(id != null ? !id.equals(recording.id) : recording.id != null);
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }
}
