package io.crowdcode.blocking.cddb;

import io.crowdcode.blocking.cddb.domain.Album;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RestController
@RequestMapping("/albums")
public class CddbBlockingController {

    private AtomicLong discCount = new AtomicLong();

    @Autowired
    private AlbumMongoRepository albumRepository;

    @PostMapping
    public ResponseEntity<Void> addCdDbEntry(@RequestBody Album album) {
        albumRepository.addEntry(album);
//        countDisc();
        return ResponseEntity.created(URI.create("/albums/" + album.getDiscId())).build();
    }

    private void countDisc() {
        long count = discCount.incrementAndGet();
        if (count % 10000 == 0) {
            log.info("Now there are {} albums", count);
        }
    }

    @GetMapping
    public ResponseEntity<List<Album>> findByArtist(@RequestParam("artist") String token) {
        List<Album> result = albumRepository.findByArtist(token);
        return ResponseEntity.ok(result);
    }


    @GetMapping(path = "/{code}")
    public ResponseEntity<Album> getAlbumByCode(@PathVariable("code") String code) {
        Optional<Album> byDiscId = albumRepository.findByDiscId(code);
        if (byDiscId.isPresent()) {
            return ResponseEntity.ok(byDiscId.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/init")
    public ResponseEntity<Void> init() {
        List<Album> dummyAlbum = DataFixture.getDummyAlbum();
        for (Album album : dummyAlbum) {
            albumRepository.addEntry(album);
        }

        for (int i = 0; i <= 100; i++) {
            Album album = DataFixture.getAlbum(i);
            albumRepository.addEntry(album);
            // system.out.println(album.getDiscId());
        }

        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = "/blocking")
    public ResponseEntity<String> blocking() {
        return ResponseEntity.ok("I AM BLOCKING");
    }

}