package io.crowdcode.benchmark.blocking.cddb;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumMongoRepository extends MongoRepository<Album, String> {

    Optional<Album> findByDiscId(String discId);

    List<Album> findByArtist(String artist);

}
