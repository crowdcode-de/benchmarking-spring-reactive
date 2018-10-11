package io.crowdcode.benchmarking.blocking.repository;


import io.crowdcode.benchmarking.blocking.jdbc.Album;
import io.crowdcode.benchmarking.blocking.jdbc.Track;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static io.crowdcode.benchmarking.blocking.repository.AlbumRowMapper.ALB_DISC_ID;

public class AlbumExtractor implements ResultSetExtractor<Collection<Album>> {

    private static final AlbumRowMapper albumRowMapper = new AlbumRowMapper();
    private static final TrackRowMapper trackRowMapper = new TrackRowMapper();

    public Collection<Album> extractData(ResultSet resultSet) throws SQLException {
        Map<String, Album> albums = new HashMap<>();
        int i = 0;
        while (resultSet.next()) {
            String id = resultSet.getString(ALB_DISC_ID);
            Album album = albums.get(id);

            if (album == null) {
                album = albumRowMapper.mapRow(resultSet, i);
                albums.put(id, album);
            }

            Track track = trackRowMapper.mapRow(resultSet, i++);
            if (album != null) {
                album.addTrack(track);
            }
        }
        return albums.values();
    }
}
