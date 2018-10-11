package io.crowdcode.reactivebenchmarking.jdbc.repository;

import io.crowdcode.reactivebenchmarking.jdbc.model.Album;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

public class AlbumFlux extends Flux<Album> {
    @Override
    public void subscribe(CoreSubscriber<? super Album> actual) {

    }
}
