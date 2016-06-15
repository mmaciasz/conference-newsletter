package pl.com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import pl.com.pollub.Main;
import pl.com.pollub.db.ConferenceChangesRepository;
import pl.com.pollub.db.ConferenceRepository;
import pl.com.pollub.db.entity.Conference;
import pl.com.pollub.db.entity.ConferenceChanges;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"pl.com.pollub.db", "pl.com.pollub.task", "pl.com.pollub.webmail", "pl.com.pollub.service", "pl.com.pollub.synchro"})
public class ContextConfiguration {

    private List<Conference> conferences;

    @Bean
    CommandLineRunner addEntities(ConferenceRepository conferenceRepository, ConferenceChangesRepository conferenceChangesRepository) {
        return args -> {
            Stream<Conference> conferenceStream = Stream.of(
                    new Conference(1, "Technologie przyszłości", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()),
                    new Conference(2, "Technologie dawniej", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()),
                    new Conference(3, "Ekonomia w praktyce", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()),
                    new Conference(4, "International it meeting", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()),
                    new Conference(5, "Technologies of USA", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()),
                    new Conference(6, "Nasa technologies", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now())
            );
            conferences = conferenceStream.collect(Collectors.toList());
            conferences.forEach(conferenceRepository::save);

            Stream.of(
                    new ConferenceChanges(conferences.get(0), "I", LocalDateTime.now(), "none", "E"),
                    new ConferenceChanges(conferences.get(1), "E", LocalDateTime.now(), "none", "E"),
                    new ConferenceChanges(conferences.get(2), "A", LocalDateTime.now(), "none", "E"),
                    new ConferenceChanges(conferences.get(3), "B", LocalDateTime.now(), "none", "E"),
                    new ConferenceChanges(conferences.get(4), "C", LocalDateTime.now(), "none", "E"),
                    new ConferenceChanges(conferences.get(5), "#", LocalDateTime.now(), "none", "E")
            ).forEach(conferenceChangesRepository::save);
        };
    }
}
