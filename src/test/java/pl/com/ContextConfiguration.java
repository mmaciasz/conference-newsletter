package pl.com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import pl.com.pollub.db.ConferenceChangesRepository;
import pl.com.pollub.db.ConferenceRepository;
import pl.com.pollub.db.entity.Conference;
import pl.com.pollub.db.entity.ConferenceChanges;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EnableAutoConfiguration
@ComponentScan
public class
ContextConfiguration {

    private List<Conference> conferences;

    @Bean
    CommandLineRunner addEntities(ConferenceRepository conferenceRepository, ConferenceChangesRepository conferenceChangesRepository) {
        return args -> {
            Stream<Conference> conferenceStream = Stream.of(
                    new Conference(1, "Technologie przyszłości", new Date(), new Date(), new Date()),
                    new Conference(2, "Technologie dawniej", new Date(), new Date(), new Date()),
                    new Conference(3, "Ekonomia w praktyce", new Date(), new Date(), new Date()),
                    new Conference(4, "International it meeting", new Date(), new Date(), new Date()),
                    new Conference(5, "Technologies of USA", new Date(), new Date(), new Date()),
                    new Conference(6, "Nasa technologies", new Date(), new Date(), new Date())
            );
            conferences = conferenceStream.collect(Collectors.toList());
            conferences.forEach(conferenceRepository::save);

            Stream.of(
                    new ConferenceChanges(conferences.get(0), "I", new Date(), "none", "E"),
                    new ConferenceChanges(conferences.get(1), "E", new Date(), "none", "E"),
                    new ConferenceChanges(conferences.get(2), "A", new Date(), "none", "E"),
                    new ConferenceChanges(conferences.get(3), "B", new Date(), "none", "E"),
                    new ConferenceChanges(conferences.get(4), "C", new Date(), "none", "E"),
                    new ConferenceChanges(conferences.get(5), "#", new Date(), "none", "E")
            ).forEach(conferenceChangesRepository::save);
        };
    }
}
