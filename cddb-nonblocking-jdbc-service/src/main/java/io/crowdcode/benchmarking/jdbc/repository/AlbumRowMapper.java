package io.crowdcode.benchmarking.jdbc.repository;

import com.github.pgasync.Row;
import io.crowdcode.benchmarking.jdbc.model.Album;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AlbumRowMapper {

    public static final String ALBUM_TABLE = "ALBUM";

    public static final String ALB_DISC_ID = "ALB_DISC_ID";
    public static final String ALB_ARTIST = "ALB_ARTIST";
    public static final String ALB_GENRE = "ALB_GENRE";
    public static final String ALB_NAME = "ALB_NAME";
    public static final String ALB_YEAR = "ALB_YEAR";
    public static final String ALB_HASHVALUE = "ALB_HASH";

    public static final List<String> ELEMENTS = Arrays.asList(ALB_DISC_ID,
            ALB_ARTIST,
            ALB_GENRE,
            ALB_NAME,
            ALB_YEAR,
            ALB_HASHVALUE);

    public static final String ALBUM_COLUMNS = String.join(",", ELEMENTS);

    public static final String ALBUM_PARAMS = String.join(",", Stream.iterate(1, i -> i + 1)
            .limit(ELEMENTS.size())
            .map(i -> "$" + i)
            .collect(Collectors.toList()));


    public Album mapRow(Row row, int i) throws SQLException {
        Album result = new Album();
        result.setDiscId(row.getString(ALB_DISC_ID));
        result.setArtist(row.getString(ALB_ARTIST));
        result.setGenre(row.getString(ALB_GENRE));
        result.setName(row.getString(ALB_NAME));
        result.setYear(row.getInt(ALB_YEAR));
        result.setHashValue(row.getString(ALB_HASHVALUE));
        return result;
    }
}
