package io.crowdcode.blocking.cddb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
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

    public void setAllTracks(Track... tracks) {
        List<Track> trackList = new ArrayList<>();
        for (Track t : tracks) {
            trackList.add(t);
        }
        this.tracks = trackList;
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
