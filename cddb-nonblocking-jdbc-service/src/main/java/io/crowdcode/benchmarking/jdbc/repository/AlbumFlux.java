package io.crowdcode.benchmarking.jdbc.repository;

import io.crowdcode.benchmarking.jdbc.model.Album;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

public class AlbumFlux extends Flux<Album> {
    @Override
    public void subscribe(CoreSubscriber<? super Album> actual) {

    }
}
