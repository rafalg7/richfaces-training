package pl.itcrowd.tutorials.richfaces.view;

import pl.itcrowd.tutorials.richfaces.dao.AlbumDAO;
import pl.itcrowd.tutorials.richfaces.domain.Album;
import pl.itcrowd.tutorials.richfaces.domain.Track;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: Rafal Gielczowski
 * Date: 2/5/13 Time: 9:54 AM
 */

@ViewScoped
@ManagedBean
public class AlbumsListView {

    @ManagedProperty(value = "#{albumDAO}")
    private AlbumDAO albumDAO;

    List<Album> albumList;
    Map<Album, Boolean> selectedAlbums = new HashMap<Album, Boolean>();

    //lazy initialization
    public List<Album> getAlbumList()
    {
        if(null==albumList) {
            albumList=albumDAO.getAllAlbums();
        }
        return albumList;
    }

    public String getUserLanguage(){
        return FacesContext.getCurrentInstance().getViewRoot().getLocale().getISO3Language();
    }

    public AlbumDAO getAlbumDAO()
    {
        return albumDAO;
    }

    public void setAlbumDAO(AlbumDAO albumDAO)
    {
        this.albumDAO = albumDAO;
    }

    public Map<Album, Boolean> getSelectedAlbums()
    {
        if(null==selectedAlbums){
            selectedAlbums = new HashMap<Album, Boolean>();
            for(Album album : albumList){
                selectedAlbums.put(album, false);
            }
        }
        return selectedAlbums;
    }

    public void setSelectedAlbums(Map<Album, Boolean> selectedAlbums)
    {
        this.selectedAlbums = selectedAlbums;
    }

    public void removeSelectedAlbums(){
        Set<Album> keys = getSelectedAlbums().keySet();
        for(Album key : keys){
            if(selectedAlbums.get(key)){
                albumDAO.deleteAlbum(key);
            }
        }
    }
}
