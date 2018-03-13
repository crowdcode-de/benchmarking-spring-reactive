package io.crowdcode.blocking.cddb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Setter
@Getter
@Accessors(chain = true)
public class Track implements Serializable {

    private static final long serialVersionUID = -1234567L;

    private String title;
    private int trackNo;

    public Track(String title, int trackNo) {
        this.title = title;
        this.trackNo = trackNo;
    }
}