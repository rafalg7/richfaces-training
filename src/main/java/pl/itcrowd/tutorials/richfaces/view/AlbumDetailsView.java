package pl.itcrowd.tutorials.richfaces.view;

import pl.itcrowd.tutorials.richfaces.dao.AlbumDAO;
import pl.itcrowd.tutorials.richfaces.dao.ArtistDAO;
import pl.itcrowd.tutorials.richfaces.dao.EnsembleDAO;
import pl.itcrowd.tutorials.richfaces.dao.RecordingDAO;
import pl.itcrowd.tutorials.richfaces.domain.Album;
import pl.itcrowd.tutorials.richfaces.domain.AlbumTranslation;
import pl.itcrowd.tutorials.richfaces.domain.Artist;
import pl.itcrowd.tutorials.richfaces.domain.Ensemble;
import pl.itcrowd.tutorials.richfaces.domain.Recording;
import pl.itcrowd.tutorials.richfaces.domain.Track;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * User: Rafal Gielczowski
 * Date: 2/5/13 Time: 9:54 AM
 */
@ViewScoped
@ManagedBean
public class AlbumDetailsView {

    @ManagedProperty(value = "#{albumDAO}")
    private AlbumDAO albumDAO;

    @ManagedProperty(value = "#{ensembleDAO}")
    private EnsembleDAO ensembleDAO;

    @ManagedProperty(value = "#{artistDAO}")
    private ArtistDAO artistDAO;

    @ManagedProperty(value = "#{recordingDAO}")
    private RecordingDAO recordingDAO;

    private Album selectedAlbum;

    private Map<Track, Boolean> selectedTracks;
    private Map<Recording, Boolean> selectedRecordings;

    private List<Ensemble> ensemblesList;
    private List<Artist> availableArtists;
    private List<String> availableLanguages;
    private List<Recording> availableRecordings;

    public AlbumDetailsView()
    {
        initAlbum();
    }

    public void initAlbum(){
        this.selectedAlbum = new Album();
        HashMap<String, AlbumTranslation> translations = new HashMap<String, AlbumTranslation>();

        for(String lang : getAvailableLanguages()){
            translations.put(lang, new AlbumTranslation(selectedAlbum, (long)Math.random() , lang, ""));
        }
        selectedAlbum.setTranslations(translations);

        selectedTracks=null;
        selectedRecordings=null;
    }

    public void newAction(){
        initAlbum();
    }

    public String saveAction(){
        albumDAO.saveAlbum(selectedAlbum);
        return "";
    }

    public String setCurrentAlbum(Album album){
        this.selectedAlbum = album;
        return "";
    }

    public List<String> getAvailableLanguages(){
        if(null==availableLanguages){
            List<String> availableLanguages = new ArrayList<String>();
            Iterator<Locale> supportedLocales = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
            while (supportedLocales.hasNext()){
                availableLanguages.add(supportedLocales.next().getISO3Language());
            }
            this.availableLanguages=availableLanguages;
        }
        return availableLanguages;
    }

    public List<Recording> getAvailableRecordings()
    {
        if(null==availableRecordings){
            availableRecordings=recordingDAO.getAllRecordings();
        }
        return availableRecordings;
    }

    public List<Track> getTracksAsList(){
        return new ArrayList<Track>(selectedAlbum.getTracks());
    }

    public List<Ensemble> getEnsemblesList()
    {
        if(null== ensemblesList){
            ensemblesList =ensembleDAO.getAllEnsemble();
        }
        return ensemblesList;
    }

    public List<Artist> getAvailableArtists()
    {
        if(null==availableArtists){
            availableArtists=artistDAO.getAllArtists();
        }
        return availableArtists;
    }

    public Album getSelectedAlbum()
    {
        return selectedAlbum;
    }

    public AlbumDAO getAlbumDAO()
    {
        return albumDAO;
    }

    public void setAlbumDAO(AlbumDAO albumDAO)
    {
        this.albumDAO = albumDAO;
    }

    public EnsembleDAO getEnsembleDAO()
    {
        return ensembleDAO;
    }

    public void setEnsembleDAO(EnsembleDAO ensembleDAO)
    {
        this.ensembleDAO = ensembleDAO;
    }

    public ArtistDAO getArtistDAO()
    {
        return artistDAO;
    }

    public void setArtistDAO(ArtistDAO artistDAO)
    {
        this.artistDAO = artistDAO;
    }

    private Converter ensembleConverter = new Converter() {
        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value)
        {
            if(value==null || value.isEmpty()){
                return null;
            }

            for(Ensemble e : getEnsemblesList()){
                if(e.getId().equals(Long.parseLong(value))){
                    return e;
                }
            }
            return "";
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value)
        {
            if(null!=value && value instanceof Ensemble){
                return ((Ensemble)value).getId().toString();
            }else{
                return "";
            }
        }
    };

    private Converter artistConverter = new Converter() {
        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value)
        {
            if(value==null || value.isEmpty()){
                return null;
            }

            for(Artist a : getAvailableArtists()){
                if(a.getId().equals(Long.parseLong(value))){
                    return a;
                }
            }
            return "";
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value)
        {
            if(null!=value && value instanceof Artist){
                return ((Artist)value).getId().toString();
            }else{
                return "";
            }
        }
    };

    public Converter getEnsembleConverter()
    {
        return ensembleConverter;
    }

    public Converter getArtistConverter()
    {
        return artistConverter;
    }

    public Map<Recording, Boolean> getSelectedRecordings()
    {
        if(null==selectedRecordings){
            selectedRecordings = new HashMap<Recording, Boolean>();
            for(Recording recording : availableRecordings){
                selectedRecordings.put(recording, false);
            }
        }
        return selectedRecordings;
    }

    public void setSelectedRecordings(Map<Recording, Boolean> selectedRecordings)
    {
        this.selectedRecordings = selectedRecordings;
    }

    public String addRecordingsAction(){
        Set<Recording> keys = getSelectedRecordings().keySet();
        for(Recording key : keys){
            //if selected
            if(selectedRecordings.get(key)){
                Track track = new Track();
                track.setAlbum(this.selectedAlbum);
                track.setRecording(key);
                selectedAlbum.getTracks().add(track);
            }
        }
        return "";
    }

    public Map<Track, Boolean> getSelectedTracks()
    {
        if(null==selectedTracks){
            selectedTracks = new HashMap<Track, Boolean>();
            for(Track track : getSelectedAlbum().getTracks()){
                selectedTracks.put(track, false);
            }
        }
        return selectedTracks;
    }

    public void setSelectedTracks(Map<Track, Boolean> selectedTracks)
    {
        this.selectedTracks = selectedTracks;
    }

    public void removeSelectedTracks(){
        for(Map.Entry<Track, Boolean> entry : getSelectedTracks().entrySet()){
            if(entry.getValue()){
                selectedAlbum.getTracks().remove(entry.getKey());
            }
        }
    }

    public RecordingDAO getRecordingDAO()
    {
        return recordingDAO;
    }

    public void setRecordingDAO(RecordingDAO recordingDAO)
    {
        this.recordingDAO = recordingDAO;
    }
}
