package mobile.assignment.backend.service;

import mobile.assignment.backend.model.Artwork;
import mobile.assignment.backend.model.Collection;
import mobile.assignment.backend.model.Museum;
import mobile.assignment.backend.repository.ArtworkRepository;
import mobile.assignment.backend.repository.CollectionRepository;
import mobile.assignment.backend.repository.MuseumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortalService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private ArtworkRepository artworkRepository;

    @Autowired
    private MuseumRepository museumRepository;

    public List<Collection> getAllCollections() {
        return collectionRepository.findAll();
    }

    public List<Artwork> getAllArtworks() {
        return artworkRepository.findAll();
    }

    public Museum getMuseumInfo() {
        // Assuming there's only one entry for museum general info
        return museumRepository.findAll().stream().findFirst().orElse(null);
    }
}
