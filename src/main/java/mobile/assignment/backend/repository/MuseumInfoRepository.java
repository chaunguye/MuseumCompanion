package mobile.assignment.backend.repository;

import mobile.assignment.backend.model.MuseumInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuseumInfoRepository extends JpaRepository<MuseumInfo, Long> {
}
