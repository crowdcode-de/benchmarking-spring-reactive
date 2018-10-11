package io.crowdcode.benchmarking.blocking.repository;

import io.crowdcode.benchmarking.blocking.jdbc.Album;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;


public class AlbumRowMapper implements RowMapper<Album> {

    public static final String ALBUM_TABLE = "ALBUM";

    public static final String ALB_DISC_ID = "ALB_DISC_ID";
    public static final String ALB_ARTIST = "ALB_ARTIST";
    public static final String ALB_GENRE = "ALB_GENRE";
    public static final String ALB_NAME = "ALB_NAME";
    public static final String ALB_YEAR = "ALB_YEAR";

    public static final String ALBUM_COLUMNS = String.join(","
            , Arrays.asList(ALB_DISC_ID,
                    ALB_ARTIST,
                    ALB_GENRE,
                    ALB_NAME,
                    ALB_YEAR));


    @Override
    public Album mapRow(ResultSet resultSet, int i) throws SQLException {
        Album result = new Album();
        result.setDiscId(resultSet.getString(ALB_DISC_ID));
        result.setArtist(resultSet.getString(ALB_ARTIST));
        result.setGenre(resultSet.getString(ALB_GENRE));
        result.setName(resultSet.getString(ALB_NAME));
        result.setYear(resultSet.getInt(ALB_YEAR));
        return result;
    }
}
