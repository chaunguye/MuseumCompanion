package mobile.assignment.backend.repository;

import mobile.assignment.backend.model.Portal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface PortalRepository extends JpaRepository<Portal, UUID> {
}
