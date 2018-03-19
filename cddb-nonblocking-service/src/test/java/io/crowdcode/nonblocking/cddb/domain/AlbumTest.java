package io.crowdcode.nonblocking.cddb.domain;

import io.crowdcode.nonblocking.cddb.Album;
import io.crowdcode.nonblocking.cddb.Track;
import org.junit.Test;

public class AlbumTest {

    @Test
    public void testAlum() {
        Album album = new Album();
        album.setAllTracks(new Track(), new Track());

    }
}