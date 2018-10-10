package io.crowdcode.benchmark.blocking.cddb;

import lombok.extern.slf4j.Slf4j;
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
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/albums")
public class CddbBlockingController {

    @Autowired
    private AlbumMongoRepository albumRepository;

    @PostMapping
    public ResponseEntity<Void> addCdDbEntry(@RequestBody Album album) {
        albumRepository.save(album);
        return ResponseEntity.created(URI.create("/albums/" + album.getDiscId())).build();
    }

    @GetMapping
    public ResponseEntity<List<Album>> findByArtist(@RequestParam(value = "artist", required = false) String token) {
        List<Album> result = albumRepository.findByArtist(token);
        return ResponseEntity.ok(result);
    }


    @GetMapping(path = "/{discId}")
    public ResponseEntity<Album> getAlbumByDiscId(@PathVariable(value = "discId") String discId) {
        Optional<Album> byDiscId = albumRepository.findByDiscId(discId);
        if (byDiscId.isPresent()) {
            return ResponseEntity.ok(byDiscId.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/init")
    public ResponseEntity<Void> init() {
        DataFixture.getDummyAlbum().forEach(a -> albumRepository.save(a));

        for (int i = 0; i <= 100; i++) {
            Album album = DataFixture.getAlbum(i);
            albumRepository.save(album);
            log.info(album.getDiscId());
        }

        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = "/blocking")
    public ResponseEntity<String> blocking() {
        return ResponseEntity.ok("I AM BLOCKING MONGO SERVICE");
    }

}
