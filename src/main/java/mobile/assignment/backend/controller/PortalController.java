package mobile.assignment.backend.controller;

import mobile.assignment.backend.model.Artwork;
import mobile.assignment.backend.model.Collection;
import mobile.assignment.backend.model.Museum;
import mobile.assignment.backend.service.PortalService;
import mobile.assignment.backend.service.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;


import java.util.List;

@RestController
@RequestMapping("/portal")
public class PortalController {

    @Autowired
    private PortalService portalService;

    @Autowired
    private ArtworkService artworkService;

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getPortalInfo(
        @RequestParam(name = "category", required = false) String category) {
        Map<String, Object> response = new HashMap<>();
        response.put("daily_highlight", portalService.getAllPortals());
        response.put("artworks", artworkService.getAllArtworks());
        return ResponseEntity.ok(response);
    }

    
}
