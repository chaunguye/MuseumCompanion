package mobile.assignment.backend.controller;

import mobile.assignment.backend.model.Artwork;
import mobile.assignment.backend.model.Exhibition;
import mobile.assignment.backend.model.MuseumInfo;
import mobile.assignment.backend.service.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portal")
public class PortalController {

    @Autowired
    private PortalService portalService;

    @GetMapping("/exhibitions")
    public List<Exhibition> getExhibitions() {
        return portalService.getAllExhibitions();
    }

    @GetMapping("/artworks/search")
    public List<Artwork> searchArtworks(@RequestParam String query) {
        return portalService.searchArtworks(query);
    }

    @GetMapping("/museum-info")
    public MuseumInfo getMuseumInfo() {
        return portalService.getMuseumInfo();
    }
}
