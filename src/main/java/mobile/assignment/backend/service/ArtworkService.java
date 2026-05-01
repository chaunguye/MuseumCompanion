package mobile.assignment.backend.service;

import java.util.List;
import java.util.UUID;
import mobile.assignment.backend.dto.ArtworkRequest;
import mobile.assignment.backend.model.Artwork;
import mobile.assignment.backend.model.Collection;
import mobile.assignment.backend.model.Room;
import mobile.assignment.backend.repository.ArtworkRepository;
import mobile.assignment.backend.repository.CollectionRepository;
import mobile.assignment.backend.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ArtworkService {

    private final ArtworkRepository artworkRepository;
    private final RoomRepository roomRepository;
    private final CollectionRepository collectionRepository;

    public ArtworkService(
            ArtworkRepository artworkRepository,
            RoomRepository roomRepository,
            CollectionRepository collectionRepository
    ) {
        this.artworkRepository = artworkRepository;
        this.roomRepository = roomRepository;
        this.collectionRepository = collectionRepository;
    }

    public List<Artwork> getAllArtworks() {
        return artworkRepository.findAll();
    }

    public Artwork getArtwork(UUID id) {
        return artworkRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artwork not found"));
    }

    public Artwork createArtwork(ArtworkRequest request) {
        validateRequiredFields(request);
        ensureQrCodeTokenIsAvailable(request.qrCodeToken(), null);

        Artwork artwork = new Artwork();
        applyRequest(artwork, request);
        return artworkRepository.save(artwork);
    }

    public Artwork updateArtwork(UUID id, ArtworkRequest request) {
        validateRequiredFields(request);
        Artwork artwork = getArtwork(id);
        ensureQrCodeTokenIsAvailable(request.qrCodeToken(), id);

        applyRequest(artwork, request);
        return artworkRepository.save(artwork);
    }

    public void deleteArtwork(UUID id) {
        Artwork artwork = getArtwork(id);
        artworkRepository.delete(artwork);
    }

    private void applyRequest(Artwork artwork, ArtworkRequest request) {
        artwork.setRoom(resolveRoom(request.roomId()));
        artwork.setCollection(resolveCollection(request.collectionId()));
        artwork.setTitle(request.title().trim());
        artwork.setAuthor(cleanOptionalText(request.author()));
        artwork.setDescription(cleanOptionalText(request.description()));
        artwork.setAudioUrl(cleanOptionalText(request.audioUrl()));
        artwork.setImageUrl(cleanOptionalText(request.imageUrl()));
        artwork.setQrCodeToken(request.qrCodeToken().trim());
    }

    private Room resolveRoom(UUID roomId) {
        if (roomId == null) {
            return null;
        }

        return roomRepository.findById(roomId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room not found"));
    }

    private Collection resolveCollection(UUID collectionId) {
        if (collectionId == null) {
            return null;
        }

        return collectionRepository.findById(collectionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Collection not found"));
    }

    private void ensureQrCodeTokenIsAvailable(String qrCodeToken, UUID artworkId) {
        artworkRepository.findByQrCodeToken(qrCodeToken.trim())
                .filter(existingArtwork -> !existingArtwork.getId().equals(artworkId))
                .ifPresent(existingArtwork -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "QR code token already exists");
                });
    }

    private void validateRequiredFields(ArtworkRequest request) {
        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Artwork request body is required");
        }
        if (isBlank(request.title())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title is required");
        }
        if (isBlank(request.qrCodeToken())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "QR code token is required");
        }
    }

    private String cleanOptionalText(String value) {
        return isBlank(value) ? null : value.trim();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
