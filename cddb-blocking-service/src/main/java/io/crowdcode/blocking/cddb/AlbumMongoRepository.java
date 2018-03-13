package io.crowdcode.blocking.cddb;


import io.crowdcode.blocking.cddb.domain.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AlbumMongoRepository extends MongoRepository<Album, String> {

    Optional<Album> findByDiscId(String discId);

    List<Album> findByArtist(String artist);

    Album getByDiscId(String discId);

    default Album getByCode(String code) {
        return getByDiscId(code);
    }


}
