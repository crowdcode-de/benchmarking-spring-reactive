package io.crowdcode.nonblocking.cddb;

import io.crowdcode.blocking.cddb.domain.Album;
import io.crowdcode.nonblocking.cddb.AlbumMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.xml.ws.Response;
import java.net.URI;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RestController
@RequestMapping("/albums")
public class CddbNonBlockingController {

//    private AtomicLong discCount = new AtomicLong();

    @Autowired
    private AlbumMongoRepository albumRepository;


    @PostMapping
    public Mono<ResponseEntity<Void>> addCdDbEntry(@RequestBody Album album) {
        Mono<Album> save = albumRepository.save(album);
//        countDisc();
        return save.map(a -> ResponseEntity.created(URI.create("/albums/" + album.getDiscId())).build());
    }

//    private void countDisc() {
//        long count = discCount.incrementAndGet();
//        if (count % 10000 == 0) {
//            log.info("Now there are {} albums", count);
//        }
//    }

    @GetMapping
    public Flux<Album> findByArtist(@RequestParam("artist") String artist) {
        return albumRepository.findByArtist(artist);
    }


    @GetMapping(path = "/{code}")
    public Mono<Album> getAlbumByCode(@PathVariable("code") String code) {
        return albumRepository.findByDiscId(code);
    }

    @GetMapping(path = "/init")
    public ResponseEntity<Void> init() {
        DataFixture.getDummyAlbum().forEach(albumRepository::save);
        for (int i = 0; i <= 100; i++) {
            Album album = DataFixture.getAlbum(i);
            albumRepository.save(album);
        }

        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = "/blocking")
    public ResponseEntity<String> blocking() {
        return ResponseEntity.ok("I AM BLOCKING");
    }

}
