package io.crowdcode.nonblocking.cddb;

import io.crowdcode.blocking.cddb.domain.Album;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/albums")
public class CddbNonBlockingController {

    @Autowired
    private AlbumMongoRepository albumRepository;


    @PostMapping
    public Mono<ResponseEntity<Void>> addCdDbEntry(@RequestBody Album album) {
        Mono<Album> save = albumRepository.save(album);
        return save.map(a -> ResponseEntity.created(URI.create("/albums/" + album.getDiscId())).build());
    }

    @GetMapping
    public Flux<Album> findByArtist(@RequestParam("artist") String artist) {
        return albumRepository.findByArtist(artist);
    }


    @GetMapping(path = "/{discId}")
    public Mono<Album> getAlbumByCode(@PathVariable("discId") String discId) {
        return albumRepository.findByDiscId(discId);
    }

    @GetMapping(path = "/init")
    public ResponseEntity<Void> init() {
        DataFixture.getDummyAlbum().forEach(a -> albumRepository.save(a).block());
        Flux.range(0,100)
                .map(DataFixture::getAlbum)
                .map(albumRepository::save)
                .log()
                .subscribe(a->a.block());
        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = "/blocking")
    public ResponseEntity<String> blocking() {
        return ResponseEntity.ok("I AM NONBLOCKING");
    }

}
