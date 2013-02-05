package pl.itcrowd.tutorials.richfaces.domain;

public class AlbumTranslation {

    private Album album;

    private Long id;

    private String language;

    private String translation;

    public AlbumTranslation()
    {
    }

    public AlbumTranslation(Album album, Long id, String language, String translation)
    {
        this.album = album;
        this.id = id;
        this.language = language;
        this.translation = translation;
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

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getTranslation()
    {
        return translation;
    }

    public void setTranslation(String translation)
    {
        this.translation = translation;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AlbumTranslation)) {
            return false;
        }

        AlbumTranslation that = (AlbumTranslation) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }
}
