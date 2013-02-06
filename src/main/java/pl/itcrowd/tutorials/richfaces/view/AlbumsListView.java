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
import java.util.logging.Logger;

/**
 * User: Rafal Gielczowski
 * Date: 2/5/13 Time: 9:54 AM
 */

@ViewScoped
@ManagedBean
public class AlbumsListView {

    private final static Logger LOGGER = Logger.getLogger(AlbumsListView.class.getCanonicalName());

    @ManagedProperty(value = "#{albumDAO}")
    private AlbumDAO albumDAO;

    List<Album> albumList;
    Map<Album, Boolean> selectedAlbums = new HashMap<Album, Boolean>();

    //lazy initialization
    public List<Album> getAlbumList()
    {
        LOGGER.info("getAlbumList");
        if(null==albumList) {
            albumList=albumDAO.getAllAlbums();
        }
        return albumList;
    }

    public String getUserLanguage(){
        LOGGER.info("getUserLanguage");
        return FacesContext.getCurrentInstance().getViewRoot().getLocale().getISO3Language();
    }

    public AlbumDAO getAlbumDAO()
    {
        LOGGER.info("getAlbumDAO");
        return albumDAO;
    }

    public void setAlbumDAO(AlbumDAO albumDAO)
    {
        LOGGER.info("setAlbumDAO");
        this.albumDAO = albumDAO;
    }

    public Map<Album, Boolean> getSelectedAlbums()
    {
        LOGGER.info("getSelectedAlbums");
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
        LOGGER.info("setSelectedAlbums");
        this.selectedAlbums = selectedAlbums;
    }

    public void removeSelectedAlbums(){
        LOGGER.info("removeSelectedAlbums");
        Set<Album> keys = getSelectedAlbums().keySet();
        for(Album key : keys){
            if(selectedAlbums.get(key)){
                albumDAO.deleteAlbum(key);
            }
        }
    }
}
