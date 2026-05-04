package mobile.assignment.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mobile.assignment.backend.model.Portal;
import mobile.assignment.backend.repository.PortalRepository;

@Service
public class PortalService {
    @Autowired
    private PortalRepository portalRepository;

    public List<Portal> getAllPortals() {
        return portalRepository.findAll();
    }
}
