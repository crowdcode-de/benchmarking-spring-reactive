package io.crowdcode.benchmarking.jdbc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Album implements Serializable {

    private static final long serialVersionUID = -122234567L;

    private String discId;
    private String name;
    private String artist;
    private String genre;
    private Integer year;
    private List<Track> tracks;
    private String hashValue;

    public Album() {
    }


    public Album(String discId, String name, String artist, String genre, int year, List<Track> tracks) {
        this.discId = discId;
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.year = year;

        if (tracks != null && !tracks.isEmpty()) {
            this.tracks = tracks;
        } else {
            this.tracks = new ArrayList<>();
        }
    }

    public static Album from(Album album) {

        if (album != null) {
            return album;
        }
        return new Album();
    }

    public String getDiscId() {
        return discId;
    }

    public void setDiscId(String discId) {
        this.discId = discId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
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

    public void setAllTracks(Track... tracks) {
        List<Track> trackList = new ArrayList<>();
        Collections.addAll(trackList, tracks);
        this.tracks = trackList;
    }


    public void addTrack(Track track) {
        if (tracks == null) {
            tracks = new ArrayList<>();
        }
        tracks.add(track);
    }
}
