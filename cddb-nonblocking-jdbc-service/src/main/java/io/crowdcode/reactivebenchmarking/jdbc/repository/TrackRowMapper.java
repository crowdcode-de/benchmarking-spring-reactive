package io.crowdcode.reactivebenchmarking.jdbc.repository;

import com.github.pgasync.Row;
import io.crowdcode.reactivebenchmarking.jdbc.model.Track;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TrackRowMapper {

    public static final String TRACK_NUMBER = "TRK_NUMBER";
    public static final String TRACK_NAME = "TRK_NAME";
    public static final String TRACK_ALBUM = "TRK_ALB_ID";

    public static final String TRACK_TABLE = "TRACK";

    public static final List<String> ELEMENTS = Arrays.asList(TRACK_NUMBER,
            TRACK_NAME,
            TRACK_ALBUM);
    public static final String TRACK_COLUMNS = String.join(",", ELEMENTS);

    public static final String TRACK_PARAMS = String.join(",", Stream.iterate(1, i -> i + 1)
            .limit(ELEMENTS.size())
            .map(i -> "$" + i)
            .collect(Collectors.toList()));


    public Track mapRow(Row row, int i) throws SQLException {
        Track result = new Track();
        result.setTitle(row.getString(TRACK_NAME));
        result.setTrackNo(row.getInt(TRACK_NUMBER));
        return result;
    }
}
