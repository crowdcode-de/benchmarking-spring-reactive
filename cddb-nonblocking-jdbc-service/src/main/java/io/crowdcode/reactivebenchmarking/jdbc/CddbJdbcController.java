package io.crowdcode.reactivebenchmarking.jdbc;


import io.crowdcode.reactivebenchmarking.jdbc.model.Album;
import io.crowdcode.reactivebenchmarking.jdbc.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;

@RestController
@RequestMapping("/albums")
public class CddbJdbcController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private HashGenerator hashGenerator;

    @Autowired
    private DataFixture dataFixture;


    @PostMapping
    public ResponseEntity<Void> addCdDbEntry(@RequestBody Album album) {
        hashGenerator.generateHashFromAlbum(album);
        albumRepository.save(album);
        return ResponseEntity.created(URI.create("/albums/" + album.getDiscId())).build();
    }


    @GetMapping
    public Flux<Album> findByArtist(@RequestParam("artist") String artist) {
        final Flux<Album> albumFlux = Optional.ofNullable(albumRepository
                .findByArtist(artist))
                .orElseGet(Flux::empty)
                .map(Album::from);
        albumFlux.map(hashGenerator::verifyGeneratedHashFromAlbum);
        return albumFlux;
    }


    @GetMapping(path = "/{code}")
    public Mono<Album> getAlbumByCode(@PathVariable("code") String code) {
        final Mono<Album> albumMono = Optional.ofNullable(albumRepository.findByDsicId(code))
                .orElseGet(Mono::empty)
                .map(Album::from);
        albumMono.map(hashGenerator::verifyGeneratedHashFromAlbum);
        return albumMono;
    }

    @GetMapping(path = "/init")
    public ResponseEntity<Void> init() {
        dataFixture.getDummyAlbum()
                .parallelStream()
                .map(hashGenerator::generateHashFromAlbum)
                .forEach(albumRepository::save);
        Flux.range(0, 10)
                .parallel()
                .map(dataFixture::getDemoAlbum)
                .map(hashGenerator::generateHashFromAlbum)
                .map(albumRepository::save);
        return ResponseEntity.accepted().build();
    }


}
