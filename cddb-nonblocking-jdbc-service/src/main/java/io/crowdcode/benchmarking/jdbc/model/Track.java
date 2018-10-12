package io.crowdcode.benchmarking.jdbc.model;

import java.io.Serializable;


public class Track implements Serializable {

    private static final long serialVersionUID = -1234567L;

    private String title;
    private int trackNo;

    public Track() {
    }

    public Track(String title, int trackNo) {
        this.title = title;
        this.trackNo = trackNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTrackNo() {
        return trackNo;
    }

    public void setTrackNo(int trackNo) {
        this.trackNo = trackNo;
    }

    @Override
    public String toString() {
        return "Track{" +
                " trackNo=" + trackNo +
                " title='" + title + '\'' +
                '}';
    }
}