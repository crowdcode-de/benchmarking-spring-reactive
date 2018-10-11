package io.crowdcode.benchmarking.blocking.jdbc;

import io.crowdcode.benchmarking.blocking.repository.AlbumJdbcRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/albums")
public class CdDbBlockingJdbcController {

    private static final Logger logger = LoggerFactory.getLogger(CdDbBlockingJdbcController.class);

    @Autowired
    private AlbumJdbcRepository albumJdbcRepository;

    @Autowired
    private HashGenerator hashGenerator;

    @Autowired
    private DataFixture dataFixture;

    @PostMapping
    public ResponseEntity<Void> addCdDbEntry(@RequestBody Album album) {
        if (album == null) {
            logger.info("NULL ALBUM PASSED");
            return ResponseEntity.badRequest().build();
        }
        hashGenerator.generateHashFromAlbum(album);
        albumJdbcRepository.save(album);
        return ResponseEntity.created(URI.create("/albums/" + album.getDiscId())).build();
    }

    @GetMapping
    public ResponseEntity<List<Album>> findByArtist(@RequestParam("artist") String artist) {
        List<Album> artistList = albumJdbcRepository.findByByArtist(artist);
        artistList.forEach(hashGenerator::verifyGeneratedHashFromAlbum);
        return ResponseEntity.ok(artistList);
    }


    @GetMapping(path = "/{code}")
    public ResponseEntity<Album> getAlbumByCode(@PathVariable("code") String code) {
        Album album = albumJdbcRepository.findByDiscId(code);
        hashGenerator.verifyGeneratedHashFromAlbum(album);
        return ResponseEntity.ok(album);
    }

    @GetMapping(path = "/init")
    public ResponseEntity<Void> init() {
        List<Album> dummyAlbum = dataFixture.getDummyAlbum();
        for (Album album : dummyAlbum) {
            hashGenerator.generateHashFromAlbum(album);
            albumJdbcRepository.save(album);
        }

        for (int i = 0; i <= 100; i++) {
            Album album = dataFixture.getAlbum(i);
            hashGenerator.generateHashFromAlbum(album);
            albumJdbcRepository.save(album);
            // logger.info((album.getDiscId());
        }

        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = "/blocking")
    public ResponseEntity<String> blocking() {
        return ResponseEntity.ok("I AM BLOCKING");
    }

}
