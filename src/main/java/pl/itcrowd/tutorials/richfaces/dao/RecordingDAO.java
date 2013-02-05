package pl.itcrowd.tutorials.richfaces.dao;

import pl.itcrowd.tutorials.richfaces.domain.Artist;
import pl.itcrowd.tutorials.richfaces.domain.Recording;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ManagedBean
@SessionScoped
public class RecordingDAO {

    private final List<Recording> recordings;

    public RecordingDAO()
    {
        final List<Artist> allArtists = new ArtistDAO().getAllArtists();
        recordings = Arrays.asList(new Recording(allArtists.get(0), 1L, "Title 1"), new Recording(allArtists.get(1), 2L, "Chorus"),
            new Recording(allArtists.get(2), 3L, "Dilema"));
    }

    public List<Recording> getAllRecordings()
    {
        return Collections.unmodifiableList(recordings);
    }
}
