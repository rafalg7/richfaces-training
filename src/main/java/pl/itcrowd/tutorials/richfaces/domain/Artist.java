package pl.itcrowd.tutorials.richfaces.domain;

import java.io.Serializable;

public class Artist implements Serializable {

    private Long id;

    private String name;

    public Artist()
    {
    }

    public Artist(Long id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Artist)) {
            return false;
        }

        Artist artist = (Artist) o;

        return !(id != null ? !id.equals(artist.id) : artist.id != null);
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }
}
