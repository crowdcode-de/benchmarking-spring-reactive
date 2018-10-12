package io.crowdcode.benchmarking.jdbc.repository;

import com.github.pgasync.Db;
import io.crowdcode.benchmarking.jdbc.model.Album;
import io.crowdcode.benchmarking.jdbc.model.Track;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import rx.Subscription;

import java.util.ArrayList;
import java.util.List;

import static io.crowdcode.benchmarking.jdbc.repository.AlbumRowMapper.ALBUM_COLUMNS;
import static io.crowdcode.benchmarking.jdbc.repository.AlbumRowMapper.ALBUM_PARAMS;
import static io.crowdcode.benchmarking.jdbc.repository.TrackRowMapper.*;

@Slf4j
@Repository
public class AlbumRepository {


    private static final String selectAlbumById = "SELECT " + ALBUM_COLUMNS + "," + TRACK_COLUMNS
            + " FROM "
            + AlbumRowMapper.ALBUM_TABLE + " alb LEFT JOIN " + TRACK_TABLE + " trk"
            + " on " + AlbumRowMapper.ALB_DISC_ID + " = " + TRACK_ALBUM
            + " WHERE " + AlbumRowMapper.ALB_DISC_ID + " = $1 "
            + " ORDER BY " + AlbumRowMapper.ALB_DISC_ID + "," + TRACK_NUMBER;


    private static final String selectAlbumByArtist = "SELECT " + ALBUM_COLUMNS + "," + TRACK_COLUMNS
            + " FROM "
            + AlbumRowMapper.ALBUM_TABLE + " alb JOIN " + TRACK_TABLE + " trk"
            + " on " + AlbumRowMapper.ALB_DISC_ID + " = " + TRACK_ALBUM
            + " WHERE " + AlbumRowMapper.ALB_ARTIST + " = $1 "
            + " ORDER BY " + AlbumRowMapper.ALB_DISC_ID + "," + TRACK_NUMBER;


    private static final String insertAlbum = "INSERT INTO ALBUM (" + ALBUM_COLUMNS + ") VALUES (" + ALBUM_PARAMS + ")";

    private static final String insertTrack = "INSERT INTO TRACK(" + TRACK_COLUMNS + ") VALUES (" + TRACK_PARAMS + ")";

    @Autowired(required = false)
    private Db db;

//    @Autowired
//    private PgPool client;


    public Album save(Album album) {
        List<Object> parameters = new ArrayList<>();
        parameters.add(album.getDiscId());
        parameters.add(album.getArtist());
        parameters.add(album.getGenre());
        parameters.add(album.getName());
        parameters.add(album.getYear());
        parameters.add(album.getHashValue());


        Subscription subscribtion = db.querySet(insertAlbum, parameters.toArray())
                .subscribe(result -> log.info("Inserted ALBUM {} rows", result.updatedRows()));

        for (Track track : album.getTracks()) {
            List<Object> trackParms = new ArrayList<>();
            trackParms.add(track.getTrackNo());
            trackParms.add(track.getTitle());
            trackParms.add(album.getDiscId());

            db.querySet(insertTrack, trackParms.toArray(new Object[trackParms.size()]))
                    .subscribe(result -> log.info("Inserted TRACK {} rows", result.updatedRows()));
        }


        return album;
    }

    public Flux<Album> findByArtist(String artist) {
        AlbumSubscriber albumSubscriber = new AlbumSubscriber();
        Flux<Album> flux =
                Flux.create(emitter ->
                {
                    Subscriber<Album> subscriber = new Subscriber<>() {
                        @Override
                        public void onSubscribe(org.reactivestreams.Subscription subscription) {
                            // NONE
                        }

                        @Override
                        public void onNext(Album album) {
                            emitter.next(album);
                            if (albumSubscriber.isDone()) {
                                emitter.complete();
                            }
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            emitter.error(throwable);
                        }

                        @Override
                        public void onComplete() {
                            emitter.next(albumSubscriber.getLastAlbum());
                            emitter.complete();
                        }
                    };
                    albumSubscriber.subscribe(subscriber);
                }, FluxSink.OverflowStrategy.BUFFER);

        db.querySet(selectAlbumByArtist, new Object[]{artist}).subscribe(albumSubscriber);
        return flux;
    }

    public Mono<Album> findByDsicId(String code) {
        AlbumSubscriber albumSubscriber = new AlbumSubscriber();
        Mono<Album> from = Mono.from(albumSubscriber);
        db.querySet(selectAlbumById, new Object[]{code}).subscribe(albumSubscriber);
        return from;
    }
}
