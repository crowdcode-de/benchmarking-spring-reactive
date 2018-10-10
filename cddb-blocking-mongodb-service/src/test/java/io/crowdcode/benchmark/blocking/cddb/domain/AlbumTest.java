package io.crowdcode.benchmark.blocking.cddb.domain;

import io.crowdcode.benchmark.blocking.cddb.Album;
import io.crowdcode.benchmark.blocking.cddb.Track;
import org.junit.jupiter.api.Test;

public class AlbumTest {

    @Test
    public void testAlum() {
        Album album = new Album();
        album.setAllTracks(new Track(), new Track());

    }
}