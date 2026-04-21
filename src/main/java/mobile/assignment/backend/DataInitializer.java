package mobile.assignment.backend;

import mobile.assignment.backend.model.Artwork;
import mobile.assignment.backend.model.Exhibition;
import mobile.assignment.backend.model.MuseumInfo;
import mobile.assignment.backend.repository.ArtworkRepository;
import mobile.assignment.backend.repository.ExhibitionRepository;
import mobile.assignment.backend.repository.MuseumInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ExhibitionRepository exhibitionRepository;

    @Autowired
    private ArtworkRepository artworkRepository;

    @Autowired
    private MuseumInfoRepository museumInfoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Initialize Museum Info
        MuseumInfo info = new MuseumInfo();
        info.setOpeningHours("9:00 AM - 6:00 PM, Tue-Sun");
        info.setLocation("123 Museum St, Culture City");
        info.setContact("+123 456 789");
        info.setPromotions("Free for students on Fridays!");
        info.setHighlights("Check out the new Renaissance Gallery.");
        museumInfoRepository.save(info);

        // Initialize Exhibition
        Exhibition ex = new Exhibition();
        ex.setTitle("Renaissance Masterpieces");
        ex.setDescription("Explore the beauty of the Renaissance period.");
        ex.setStartDate("2026-04-01");
        ex.setEndDate("2026-10-01");
        ex.setHighlights("Mona Lisa, The Last Supper");
        ex.setImageUrl("https://example.com/renaissance.jpg");
        exhibitionRepository.save(ex);

        // Initialize Artworks
        Artwork a1 = new Artwork();
        a1.setTitle("Mona Lisa");
        a1.setArtist("Leonardo da Vinci");
        a1.setDescription("A famous portrait.");
        a1.setImageUrl("https://example.com/monalisa.jpg");
        a1.setAudioUrl("https://example.com/audio/monalisa.mp3");
        a1.setQrCode("qr_monalisa");
        a1.setExhibition(ex);

        Artwork a2 = new Artwork();
        a2.setTitle("The Last Supper");
        a2.setArtist("Leonardo da Vinci");
        a2.setDescription("A mural painting.");
        a2.setExhibition(ex);

        artworkRepository.saveAll(Arrays.asList(a1, a2));
    }
}
