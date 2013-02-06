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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * User: Rafal Gielczowski
 * Date: 2/5/13 Time: 9:54 AM
 */
@ViewScoped
@ManagedBean
public class AlbumDetailsView {

    private static final Logger LOGGER = Logger.getLogger(AlbumDetailsView.class.getCanonicalName());

    @ManagedProperty(value = "#{albumDAO}")
    private AlbumDAO albumDAO;

    @ManagedProperty(value = "#{artistDAO}")
    private ArtistDAO artistDAO;

    private List<Artist> availableArtists;

    private List<String> availableLanguages;

    private List<Recording> availableRecordings;


    @ManagedProperty(value = "#{ensembleDAO}")
    private EnsembleDAO ensembleDAO;

    private List<Ensemble> ensemblesList;

    @ManagedProperty(value = "#{recordingDAO}")
    private RecordingDAO recordingDAO;

    private Album selectedAlbum;

    private Map<Recording, Boolean> selectedRecordings;

    private Map<Track, Boolean> selectedTracks;

    public AlbumDetailsView()
    {
        LOGGER.info("Constructor;");
        initAlbum();
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

    public Converter getArtistConverter()
    {
        LOGGER.info("getArtistConverter");
        return artistConverter;
    }

    public ArtistDAO getArtistDAO()
    {
        LOGGER.info("getArtistDAO");
        return artistDAO;
    }

    public void setArtistDAO(ArtistDAO artistDAO)
    {
        LOGGER.info("setArtistDAO");
        this.artistDAO = artistDAO;
    }

    public List<Artist> getAvailableArtists()
    {
        LOGGER.info("getAvailableArtists");
        if (null == availableArtists) {
            availableArtists = artistDAO.getAllArtists();
        }
        return availableArtists;
    }

    public List<String> getAvailableLanguages()
    {
        LOGGER.info("getAvailableLanguages()");
        if (null == availableLanguages) {
            List<String> availableLanguages = new ArrayList<String>();
            Iterator<Locale> supportedLocales = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
            while (supportedLocales.hasNext()) {
                availableLanguages.add(supportedLocales.next().getISO3Language());
            }
            this.availableLanguages = availableLanguages;
        }
        return availableLanguages;
    }

    public List<Recording> getAvailableRecordings()
    {
        LOGGER.info("getAvailableRecordings()");
        if (null == availableRecordings) {
            availableRecordings = recordingDAO.getAllRecordings();
        }
        return availableRecordings;
    }

    public Converter getEnsembleConverter()
    {
        LOGGER.info("getEnsembleConverter");
        return ensembleConverter;
    }

    public EnsembleDAO getEnsembleDAO()
    {
        LOGGER.info("getEnsembleDAO");
        return ensembleDAO;
    }

    public void setEnsembleDAO(EnsembleDAO ensembleDAO)
    {
        LOGGER.info("setEnsembleDAO");
        this.ensembleDAO = ensembleDAO;
    }

    public List<Ensemble> getEnsemblesList()
    {
        LOGGER.info("getEnsembleList");
        if (null == ensemblesList) {
            ensemblesList = ensembleDAO.getAllEnsemble();
        }
        return ensemblesList;
    }

    public RecordingDAO getRecordingDAO()
    {
        LOGGER.info("getRecordingDAO");
        return recordingDAO;
    }

    public void setRecordingDAO(RecordingDAO recordingDAO)
    {
        LOGGER.info("setRecordingDAO");
        this.recordingDAO = recordingDAO;
    }

    public Album getSelectedAlbum()
    {
        LOGGER.info("getSelectedAlbum");
        return selectedAlbum;
    }

    public Map<Recording, Boolean> getSelectedRecordings()
    {
        LOGGER.info("getSelectedRecordings");
        if (null == selectedRecordings) {
            selectedRecordings = new HashMap<Recording, Boolean>();
            for (Recording recording : availableRecordings) {
                selectedRecordings.put(recording, false);
            }
        }
        return selectedRecordings;
    }

    public void setSelectedRecordings(Map<Recording, Boolean> selectedRecordings)
    {
        LOGGER.info("setSelectedRecordings");
        this.selectedRecordings = selectedRecordings;
    }

    public Map<Track, Boolean> getSelectedTracks()
    {
        LOGGER.info("getSelectedTracks");
        if (null == selectedTracks) {
            selectedTracks = new HashMap<Track, Boolean>();
            for (Track track : getSelectedAlbum().getTracks()) {
                selectedTracks.put(track, false);
            }
        }
        return selectedTracks;
    }

    public void setSelectedTracks(Map<Track, Boolean> selectedTracks)
    {
        LOGGER.info("setSelectedTracks");
        this.selectedTracks = selectedTracks;
    }

    public List<Track> getTracksAsList()
    {
        LOGGER.info("getTracksAsList()");
        return new ArrayList<Track>(selectedAlbum.getTracks());
    }

    public String setCurrentAlbum(Album album)
    {
        LOGGER.info("setCurrentAlbum()");
        this.selectedAlbum = album;
        return "";
    }

    public String addRecordingsAction()
    {
        LOGGER.info("addRecordingsAction");
        Set<Recording> keys = getSelectedRecordings().keySet();
        for (Recording key : keys) {
            //if selected
            if (selectedRecordings.get(key)) {
                Track track = new Track();
                track.setAlbum(this.selectedAlbum);
                track.setRecording(key);
                selectedAlbum.getTracks().add(track);
            }
        }
        return "";
    }

    public void initAlbum()
    {
        LOGGER.info("InitAlbum()");
        this.selectedAlbum = new Album();
        HashMap<String, AlbumTranslation> translations = new HashMap<String, AlbumTranslation>();

        for (String lang : getAvailableLanguages()) {
            translations.put(lang, new AlbumTranslation(selectedAlbum, (long) Math.random(), lang, ""));
        }
        selectedAlbum.setTranslations(translations);

        selectedTracks = null;
        selectedRecordings = null;
    }

    public void newAction()
    {
        LOGGER.info("NewAction");
        initAlbum();
    }

    public void removeSelectedTracks()
    {
        LOGGER.info("removeSelectedTracks");
        for (Map.Entry<Track, Boolean> entry : getSelectedTracks().entrySet()) {
            if (entry.getValue()) {
                selectedAlbum.getTracks().remove(entry.getKey());
            }
        }
    }

    public String saveAction()
    {
        LOGGER.info("saveAction()");
        albumDAO.saveAlbum(selectedAlbum);
        return "";
    }

    private Converter ensembleConverter = new Converter() {
        private final Logger LOGGER = Logger.getLogger("EnsembleConverter");

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value)
        {
            LOGGER.info("getAsObject in ensembleConverter");
            if (value == null || value.isEmpty()) {
                return null;
            }

            return ensembleDAO.getEnsembleById(Long.parseLong(value));
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value)
        {
            LOGGER.info("getAsString");
            if (null != value && value instanceof Ensemble) {
                return ((Ensemble) value).getId().toString();
            } else {
                return "";
            }
        }
    };

    private Converter artistConverter = new Converter() {
        private final Logger LOGGER = Logger.getLogger("ArtistConverter");
        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value)
        {
            LOGGER.info("getAsObject");
            if (value == null || value.isEmpty()) {
                return null;
            }

            for (Artist a : getAvailableArtists()) {
                if (a.getId().equals(Long.parseLong(value))) {
                    return a;
                }
            }
            return "";
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value)
        {
            LOGGER.info("getAsString");
            if (null != value && value instanceof Artist) {
                return ((Artist) value).getId().toString();
            } else {
                return "";
            }
        }
    };
}
