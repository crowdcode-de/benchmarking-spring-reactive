package io.crowdcode.blocking.cddb;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AlbumMongoRepository extends MongoRepository<Album, String> {

    Optional<Album> findByDiscId(String discId);

    List<Album> findByArtist(String artist);

}
