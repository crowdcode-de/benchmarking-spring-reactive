package io.crowdcode.reactivebenchmarking.jdbc;

import io.crowdcode.reactivebenchmarking.jdbc.model.Album;
import io.crowdcode.reactivebenchmarking.jdbc.model.Track;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DataFixture {

    public List<Album> getDummyAlbum() {
        Album a1 = getAlbum1();
        Album a2 = getAlbum2();
        Album a3 = getAlbum3();
        return Arrays.asList(a1, a2, a3);

    }

    private Album getAlbum1() {
        Album a1 = new Album();
        a1.setArtist("The artist");
        a1.setDiscId("4711-1110-6667");
        a1.setGenre("Country");
        a1.setName(" Songs");
        a1.setYear(2017);

        Track a1t1 = new Track("There is a ", 1);
        Track a1t2 = new Track("Holy Moly", 2);
        Track a1t3 = new Track("Moon Shine", 3);
        Track a1t4 = new Track("There is", 4);

        a1.setAllTracks(a1t1, a1t2, a1t3, a1t4);
        return a1;
    }

    private Album getAlbum2() {
        Album a2 = new Album();

        a2.setArtist("Slomi");
        a2.setDiscId("4712-1112-6667");
        a2.setGenre("Klütermetall");
        a2.setName("Reign");
        a2.setYear(2017);

        Track a2t1 = new Track("Angel of Milk", 1);
        Track a2t2 = new Track("Rest", 2);
        Track a2t3 = new Track("Reigning", 3);
        Track a2t4 = new Track("Season", 4);

        a2.setAllTracks(a2t1, a2t2, a2t3, a2t4);
        return a2;
    }

    private Album getAlbum3() {
        Album a3 = new Album();
        a3.setArtist("Hein Blöd und seine Blaskapelle");
        a3.setDiscId("8574-8475-0374-1234");
        a3.setGenre("Incest Sounds");
        a3.setName("My father is my uncle");
        a3.setYear(2020);

        Track a3t1 = new Track("Toller Track", 1);
        Track a3t2 = new Track("Ein Glas Wasser voll Farbe", 2);
        Track a3t3 = new Track("Wir sind eine Familie", 3);
        Track a3t4 = new Track("Wer?", 4);
        a3.setAllTracks(a3t1, a3t2, a3t3, a3t4);
        return a3;
    }


    public Album getDemoAlbum(int i) {
        Album a1 = new Album();
        a1.setArtist("Emaillica");
        a1.setDiscId(i + "-4711-1110-6667");
        a1.setGenre("The genre");
        a1.setName("Pastor of Muppets");
        a1.setYear(2017);

        Track a1t1 = new Track(i + " Santa Endman", 1);
        Track a1t2 = new Track(i + " Full", 2);
        Track a1t3 = new Track(i + " Drink em All", 3);
        Track a1t4 = new Track(i + " The dog", 4);

        a1.setAllTracks(a1t1, a1t2, a1t3, a1t4);
        return a1;
    }


}
