package mobile.assignment.backend.controller;

import mobile.assignment.backend.model.Artwork;
import mobile.assignment.backend.model.Collection;
import mobile.assignment.backend.model.Museum;
import mobile.assignment.backend.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portal")
public class PortalController {

    @Autowired
    private PortalService portalService;

    @GetMapping("/collections")
    public List<Collection> getCollections() {
        return portalService.getAllCollections();
    }

    @GetMapping("/artworks")
    public List<Artwork> getArtworks() {
        return portalService.getAllArtworks();
    }

    @GetMapping("/museum-info")
    public Museum getMuseumInfo() {
        return portalService.getMuseumInfo();
    }
}
