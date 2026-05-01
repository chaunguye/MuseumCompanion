package mobile.assignment.backend.dto;

import java.util.UUID;

public record ArtworkRequest(
        UUID roomId,
        UUID collectionId,
        String title,
        String author,
        String description,
        String audioUrl,
        String imageUrl,
        String qrCodeToken
) {
}
