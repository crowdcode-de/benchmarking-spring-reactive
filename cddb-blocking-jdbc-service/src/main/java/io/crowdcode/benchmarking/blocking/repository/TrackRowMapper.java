package io.crowdcode.benchmarking.blocking.repository;

import io.crowdcode.benchmarking.blocking.jdbc.Track;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;


public class TrackRowMapper implements RowMapper<Track> {

    public static final String TRACK_NUMBER = "TRK_NUMBER";
    public static final String TRACK_NAME = "TRK_NAME";
    public static final String TRACK_ALBUM = "TRK_ALB_ID";

    public static final String TRACK_TABLE = "TRACK";

    public static final String TRACK_COLUMNS = String.join(","
            , Arrays.asList(TRACK_NUMBER,
                    TRACK_NAME,
                    TRACK_ALBUM));

    @Override
    public Track mapRow(ResultSet resultSet, int i) throws SQLException {
        Track result = new Track();
        result.setTitle(resultSet.getString(TRACK_NAME));
        result.setTrackNo(resultSet.getInt(TRACK_NUMBER));
        return result;
    }
}
