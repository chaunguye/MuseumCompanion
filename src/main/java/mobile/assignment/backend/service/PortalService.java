package mobile.assignment.backend.service;

import mobile.assignment.backend.model.Artwork;
import mobile.assignment.backend.model.Exhibition;
import mobile.assignment.backend.model.MuseumInfo;
import mobile.assignment.backend.repository.ArtworkRepository;
import mobile.assignment.backend.repository.ExhibitionRepository;
import mobile.assignment.backend.repository.MuseumInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortalService {

    @Autowired
    private ExhibitionRepository exhibitionRepository;

    @Autowired
    private ArtworkRepository artworkRepository;

    @Autowired
    private MuseumInfoRepository museumInfoRepository;

    public List<Exhibition> getAllExhibitions() {
        return exhibitionRepository.findAll();
    }

    public List<Artwork> searchArtworks(String query) {
        return artworkRepository.findByTitleContainingIgnoreCaseOrArtistContainingIgnoreCase(query, query);
    }

    public MuseumInfo getMuseumInfo() {
        // Assuming there's only one entry for museum general info
        return museumInfoRepository.findAll().stream().findFirst().orElse(null);
    }
}
