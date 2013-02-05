package pl.itcrowd.tutorials.richfaces.domain;

import java.io.Serializable;

public class Ensemble implements Serializable {

    private Long id;

    private String name;

    public Ensemble()
    {
    }

    public Ensemble(Long id, String name)
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
        if (!(o instanceof Ensemble)) {
            return false;
        }

        Ensemble ensemble = (Ensemble) o;

        return !(id != null ? !id.equals(ensemble.id) : ensemble.id != null);
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }
}
