package io.crowdcode.nonblocking.cddb;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlbumMongoRepository extends ReactiveCrudRepository<Album, String> {

    Mono<Album> findByDiscId(String discId);

    Flux<Album> findByArtist(String artist);

}
