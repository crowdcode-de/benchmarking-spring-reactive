package io.crowdcode.benchmarking.jdbc.repository;

import com.github.pgasync.ResultSet;
import com.github.pgasync.Row;
import io.crowdcode.benchmarking.jdbc.model.Album;
import io.crowdcode.benchmarking.jdbc.model.Track;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static io.crowdcode.benchmarking.jdbc.repository.AlbumRowMapper.ALB_DISC_ID;

public class AlbumSubscriber extends rx.Subscriber<ResultSet> implements Publisher<Album> {

    private static final TrackRowMapper trackRowMapper = new TrackRowMapper();
    private Subscriber<? super Album> subscriber;
    private static final AlbumRowMapper albumRowMapper = new AlbumRowMapper();
    private Map<String, Album> albums = new HashMap<>();
    private AtomicInteger count = new AtomicInteger(0);

    private Album lastAlbum = null;
    private boolean done = false;

    public boolean isDone() {
        return done;
    }

    public Album getLastAlbum() {
        return lastAlbum;
    }

    @Override
    public void onCompleted() {
        done = true;
        emitLastAlbum();
        subscriber.onComplete();
    }

    @Override
    public void onError(Throwable throwable) {
        subscriber.onComplete();
    }

    @Override
    public void onNext(ResultSet resultSet) {
        String id;
        Iterator<Row> iterator = resultSet.iterator();
        while (iterator.hasNext()) {
            try {
                Row rs = iterator.next();
                id = rs.getString(ALB_DISC_ID);
                Album album = albums.get(id);

                if (album == null) { // NEW ALBUM

                    emitLastAlbum();

                    album = albumRowMapper.mapRow(rs, count.get());
                    albums.put(id, album);
                    lastAlbum = album;
                }

                Track track = trackRowMapper.mapRow(rs, count.get());
                count.incrementAndGet();

                album.addTrack(track);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void emitLastAlbum() {
        if (lastAlbum != null) {
            if (subscriber != null) {
                subscriber.onNext(lastAlbum);
            }
        }
    }

    @Override
    public void subscribe(Subscriber<? super Album> subscriber) {
        this.subscriber = subscriber;
        subscriber.onSubscribe(new Subscription() {
            @Override
            public void request(long l) {

            }

            @Override
            public void cancel() {

            }
        });
    }
}
