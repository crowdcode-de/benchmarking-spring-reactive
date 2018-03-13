package io.crowdcode.nonblocking.cddb;


import io.crowdcode.blocking.cddb.domain.Album;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface AlbumMongoRepository extends ReactiveCrudRepository<Album, String> {

    Mono<Album> findByDiscId(String discId);

    Flux<Album> findByArtist(String artist);



}
