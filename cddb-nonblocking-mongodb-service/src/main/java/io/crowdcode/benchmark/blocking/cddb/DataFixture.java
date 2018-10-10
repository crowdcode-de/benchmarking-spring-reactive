package io.crowdcode.benchmark.blocking.cddb;


import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DataFixture {

    public static List<Album> getDummyAlbum() {
        Album a1 = getAlbum1();
        Album a2 = getAlbum2();
        Album a3 = getAlbum3();
        return Arrays.asList(a1, a2, a3);

    }

    public static Album getAlbum1() {
        Album a1 = new Album();
        a1.setArtist("The artist formerly known as the long bearded Hobo");
        a1.setDiscId("4711-1110-1112-6667");
        a1.setGenre("Country");
        a1.setName("Rectal Songs");
        a1.setYear(2018);

        Track a1t1 = new Track("There is a caterpiller in your butt", 1);
        Track a1t2 = new Track("Holy Moly", 2);
        Track a1t3 = new Track("Moon Shine Smackaroos", 3);
        Track a1t4 = new Track("There is a 12 gauge in my face", 4);

        a1.setAllTracks(a1t1, a1t2, a1t3, a1t4);
        return a1;
    }

    public static Album getAlbum2() {
        Album a2 = new Album();

        a2.setArtist("Slairrr");
        a2.setDiscId("4712-2110-1112-6667");
        a2.setGenre("Klütermetall");
        a2.setName("Reign in Yoghurt");
        a2.setYear(2017);

        Track a2t1 = new Track("Angel of Milk", 1);
        Track a2t2 = new Track("Rest in Spoons", 2);
        Track a2t3 = new Track("Reigning Lactose", 3);
        Track a2t4 = new Track("Seasons in the fermenter", 4);

        a2.setAllTracks(a2t1, a2t2, a2t3, a2t4);
        return a2;
    }

    public static Album getAlbum3() {
        Album a3 = new Album();
        a3.setArtist("Hein Blöd und seine Blaskapelle");
        a3.setDiscId("8574-8475-0374-1234");
        a3.setGenre("Incest Sounds");
        a3.setName("My father is my uncle");
        a3.setYear(2020);

        Track a3t1 = new Track("Toller Track", 1);
        Track a3t2 = new Track("Ein Glas Wasser voll Farbe", 2);
        Track a3t3 = new Track("Wir sind eine Familie", 3);
        Track a3t4 = new Track("Wer braucht schon Fremde Gene?", 4);
        a3.setAllTracks(a3t1, a3t2, a3t3, a3t4);
        return a3;
    }


    public static Album getAlbum(int i) {
        Album a1 = new Album();
        a1.setArtist(i + " Demo demo");
        a1.setDiscId(i + "-4711-1110-1112-6667");
        a1.setGenre("Country");
        a1.setName("Rectal Songs");
        a1.setYear(2017);

        Track a1t1 = new Track(i + " There is a caterpiller in your butt", 1);
        Track a1t2 = new Track(i + " Holy Moly", 2);
        Track a1t3 = new Track(i + " Moon Shine Smackaroos", 3);
        Track a1t4 = new Track(i + " There is a 12 gauge in my face", 4);

        a1.setAllTracks(a1t1, a1t2, a1t3, a1t4);
        return a1;
    }


    public static Album getDemoAlbum(int i) {
        Album a1 = new Album();
        a1.setArtist("Emaillica");
        a1.setDiscId(i + "-4711-1110-1112-6667");
        a1.setGenre("The genre formerly known as thrash");
        a1.setName("Pastor of Muppets");
        a1.setYear(2017);

        Track a1t1 = new Track(i + " Santa Endman", 1);
        Track a1t2 = new Track(i + " Full", 2);
        Track a1t3 = new Track(i + " Drink em All", 3);
        Track a1t4 = new Track(i + " The dog that failed", 4);

        a1.setAllTracks(a1t1, a1t2, a1t3, a1t4);
        return a1;
    }


}
