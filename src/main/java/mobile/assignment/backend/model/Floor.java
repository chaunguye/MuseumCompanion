package mobile.assignment.backend.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "floors")
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Integer level;

    @Column(name = "map_image_url", length = 512)
    private String mapImageUrl;

    @ManyToOne
    @JoinColumn(name = "museum_id")
    private Museum museum;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }
    public String getMapImageUrl() { return mapImageUrl; }
    public void setMapImageUrl(String mapImageUrl) { this.mapImageUrl = mapImageUrl; }
    public Museum getMuseum() { return museum; }
    public void setMuseum(Museum museum) { this.museum = museum; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
