package io.crowdcode.blocking.cddb;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
@Document
public class Album implements Serializable {

    private static final long serialVersionUID = -122234567L;

    @Id
    private String discId;
    private String name;
    private String artist;
    private String genre;
    private Integer year;
    private List<Track> tracks;

    public Album() {
    }

    public Album setAllTracks(Track... tracks) {
        this.tracks = Arrays.asList(tracks);
        return this;
    }

    @Override
    public String toString() {
        return "Album{" +
                "discId='" + discId + '\'' +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                ", tracks=" + tracks +
                '}';
    }

}
