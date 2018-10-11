package io.crowdcode.benchmarking.blocking.jdbc;


import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DataFixture {

    List<Album> getDummyAlbum() {
        Album a1 = getAlbum1();
        Album a2 = getAlbum2();
        Album a3 = getAlbum3();
        return Arrays.asList(a1, a2, a3);

    }

    public Album getAlbum1() {
        Album a1 = new Album();
        a1.setArtist("The artist formerly known as Peter");
        a1.setDiscId("4711-1110-1112-6667");
        a1.setGenre("Country");
        a1.setName("Rectal Songs");
        a1.setYear(2017);

        Track a1t1 = new Track("There is a caterpiller in your eyes", 1);
        Track a1t2 = new Track("Holy Moly", 2);
        Track a1t3 = new Track("Moon Shine", 3);
        Track a1t4 = new Track("There is a men", 4);

        a1.setAllTracks(a1t1, a1t2, a1t3, a1t4);
        return a1;
    }

    private Album getAlbum2() {
        Album a2 = new Album();

        a2.setArtist("Slairrr");
        a2.setDiscId("4712-1112-6667");
        a2.setGenre("Klütermetall");
        a2.setName("Reign in town");
        a2.setYear(2017);

        Track a2t1 = new Track("Angel of Milk", 1);
        Track a2t2 = new Track("Rest in Spoons", 2);
        Track a2t3 = new Track("Reigning", 3);
        Track a2t4 = new Track("Seasons", 4);

        a2.setAllTracks(a2t1, a2t2, a2t3, a2t4);
        return a2;
    }

    private Album getAlbum3() {
        Album a3 = new Album();
        a3.setArtist("Hein Blöd und seine Jungs");
        a3.setDiscId("8574-8475-0374");
        a3.setGenre("Origin Sounds");
        a3.setName("My father is my friend");
        a3.setYear(2020);

        Track a3t1 = new Track("Toller Track", 1);
        Track a3t2 = new Track("Ein Glas Wasser voll Farbe", 2);
        Track a3t3 = new Track("Wir sind eine Familie", 3);
        Track a3t4 = new Track("Wer braucht schon Freunde?", 4);
        a3.setAllTracks(a3t1, a3t2, a3t3, a3t4);
        return a3;
    }


    Album getAlbum(int i) {
        Album a1 = new Album();
        a1.setArtist(i + " Demo demo");
        a1.setDiscId(i + "-4711-1110-6667");
        a1.setGenre("Country");
        a1.setName("Rectal Songs");
        a1.setYear(2017);

        Track a1t1 = new Track(i + " There is a caterpiller", 1);
        Track a1t2 = new Track(i + " Holy Moly", 2);
        Track a1t3 = new Track(i + " Moon Shine", 3);
        Track a1t4 = new Track(i + " There is a 12 in my face", 4);

        a1.setAllTracks(a1t1, a1t2, a1t3, a1t4);
        return a1;
    }

}
