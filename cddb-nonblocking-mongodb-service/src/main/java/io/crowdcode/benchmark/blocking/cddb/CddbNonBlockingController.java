package io.crowdcode.benchmark.blocking.cddb;

import io.crowdcode.benchmarking.hashgenerator.HashGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;

@Slf4j
@RestController
@RequestMapping("/albums")
public class CddbNonBlockingController {

    @Autowired
    private AlbumMongoRepository albumRepository;

    @Autowired
    private HashGenerator<Album> hashGenerator;

    @PostMapping
    public Mono<ResponseEntity<Void>> addCddbEntry(@RequestBody Album album) {
        return albumRepository.save(album)
                .map(hashGenerator::generateHashFromAlbum)
                .map(a -> ResponseEntity.created(URI.create("/albums/" + album.getDiscId())).build());
    }

    @GetMapping
    public Flux<Album> findByArtist(@RequestParam("artist") String artist) {
        return albumRepository.findByArtist(artist);
    }


    @GetMapping(path = "/{discId}")
    public Mono<Album> getAlbumByCode(@PathVariable("discId") String discId) {
        return albumRepository.findByDiscId(discId).map(hashGenerator::generateHashFromAlbum);
    }

    @GetMapping(path = "/init", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Album> init() {
        DataFixture.getDummyAlbum().forEach(a -> albumRepository.save(a).block());
        return Flux.range(0, 20)
                .delayElements(Duration.ofMillis(100))
                .map(DataFixture::getAlbum)
                .map(hashGenerator::generateHashFromAlbum)
                .flatMap(albumRepository::save)
                .log();
    }

    @GetMapping(path = "/blocking")
    public ResponseEntity<String> blocking() {
        return ResponseEntity.ok("I AM NONBLOCKING MONGO CDDB SERVICE");
    }

}
