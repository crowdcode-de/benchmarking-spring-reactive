package io.crowdcode.benchmarking.blocking.repository;


import io.crowdcode.benchmarking.blocking.jdbc.Album;
import io.crowdcode.benchmarking.blocking.jdbc.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.crowdcode.benchmarking.blocking.repository.AlbumRowMapper.*;
import static io.crowdcode.benchmarking.blocking.repository.TrackRowMapper.*;


@Repository
public class AlbumJdbcRepository {

    final String selectAlbumById = "SELECT " + ALBUM_COLUMNS + "," + TRACK_COLUMNS
            + " FROM "
            + ALBUM_TABLE + " alb JOIN " + TRACK_TABLE + " trk"
            + " on " + ALB_DISC_ID + " = " + TRACK_ALBUM
            + " WHERE " + ALB_DISC_ID + " = ? "
            + "ORDER BY " + ALB_DISC_ID + "," + TRACK_NUMBER;


    final String selectAlbumByArtist = "SELECT " + ALBUM_COLUMNS + "," + TRACK_COLUMNS
            + " FROM "
            + ALBUM_TABLE + " alb JOIN " + TRACK_TABLE + " trk"
            + " on " + ALB_DISC_ID + " = " + TRACK_ALBUM
            + " WHERE " + ALB_ARTIST + " = ? "
            + "ORDER BY " + ALB_DISC_ID + "," + TRACK_NUMBER;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public Album findByDiscId(final String code) {
        Collection<Album> albums = jdbcTemplate.query(selectAlbumById, new Object[]{code}, new AlbumExtractor());
        if (albums == null || albums.isEmpty()) {
            return null;
        } else if (albums.size() > 1) {
            throw new NonUniqueResultSetException("One element was expected. I got " + albums.size());
        } else {
            final Album album = albums.iterator().next();
            return album;
        }
    }

    @Transactional
    public Album save(final Album album) {

        Integer present = jdbcTemplate.query("SELECT 1 FROM " + ALBUM_TABLE + " WHERE " + ALB_DISC_ID + " = ?"
                , new Object[]{album.getDiscId()}
                , rs -> {
                    if (rs.next()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
        );

        if (present == null || present != 1) {
            SimpleJdbcInsert insertAlbum = new SimpleJdbcInsert(jdbcTemplate).withTableName(ALBUM_TABLE);
            Map<String, Object> parameters = new HashMap<>();

            parameters.put(ALB_DISC_ID, album.getDiscId());
            parameters.put(ALB_ARTIST, album.getArtist());
            parameters.put(ALB_GENRE, album.getGenre());
            parameters.put(ALB_NAME, album.getName());
            parameters.put(ALB_YEAR, album.getYear());

            insertAlbum.execute(parameters);

            for (Track track : album.getTracks()) {
                SimpleJdbcInsert insertTrack = new SimpleJdbcInsert(jdbcTemplate).withTableName(TRACK_TABLE);
                Map<String, Object> trackParms = new HashMap<>();
                trackParms.put(TRACK_ALBUM, album.getDiscId());
                trackParms.put(TRACK_NAME, track.getTitle());
                trackParms.put(TRACK_NUMBER, track.getTrackNo());

                insertTrack.execute(trackParms);
            }

            return album;
        } else {
            return findByDiscId(album.getDiscId());
        }
    }

    @Transactional(readOnly = true)
    public List<Album> findByByArtist(final String artist) {
        Collection<Album> albums = jdbcTemplate.query(selectAlbumByArtist, new Object[]{artist}, new AlbumExtractor());
        if (albums != null) {
            return new ArrayList<>(albums);
        }
        return Collections.emptyList();
    }

    private final class NonUniqueResultSetException extends RuntimeException {
        public NonUniqueResultSetException(String message) {
            super(message);
        }
    }
}
