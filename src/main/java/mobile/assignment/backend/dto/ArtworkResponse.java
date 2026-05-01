package mobile.assignment.backend.dto;

import java.time.OffsetDateTime;
import java.util.UUID;
import mobile.assignment.backend.model.Artwork;

public record ArtworkResponse(
        UUID id,
        UUID roomId,
        UUID collectionId,
        String title,
        String author,
        String description,
        String audioUrl,
        String imageUrl,
        String qrCodeToken,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
    public static ArtworkResponse from(Artwork artwork) {
        return new ArtworkResponse(
                artwork.getId(),
                artwork.getRoom() == null ? null : artwork.getRoom().getId(),
                artwork.getCollection() == null ? null : artwork.getCollection().getId(),
                artwork.getTitle(),
                artwork.getAuthor(),
                artwork.getDescription(),
                artwork.getAudioUrl(),
                artwork.getImageUrl(),
                artwork.getQrCodeToken(),
                artwork.getCreatedAt(),
                artwork.getUpdatedAt()
        );
    }
}
