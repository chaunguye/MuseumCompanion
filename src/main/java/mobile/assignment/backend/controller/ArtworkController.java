package mobile.assignment.backend.controller;

import java.util.List;
import java.util.UUID;
import mobile.assignment.backend.dto.ArtworkRequest;
import mobile.assignment.backend.dto.ArtworkResponse;
import mobile.assignment.backend.service.ArtworkService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/artworks")
public class ArtworkController {

    private final ArtworkService artworkService;

    public ArtworkController(ArtworkService artworkService) {
        this.artworkService = artworkService;
    }

    @GetMapping
    public List<ArtworkResponse> getArtworks() {
        return artworkService.getAllArtworks().stream()
                .map(ArtworkResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public ArtworkResponse getArtwork(@PathVariable UUID id) {
        return ArtworkResponse.from(artworkService.getArtwork(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtworkResponse createArtwork(@RequestBody ArtworkRequest request) {
        return ArtworkResponse.from(artworkService.createArtwork(request));
    }

    @PutMapping("/{id}")
    public ArtworkResponse updateArtwork(@PathVariable UUID id, @RequestBody ArtworkRequest request) {
        return ArtworkResponse.from(artworkService.updateArtwork(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtwork(@PathVariable UUID id) {
        artworkService.deleteArtwork(id);
    }
}
