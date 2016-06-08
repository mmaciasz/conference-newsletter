package pl.com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import pl.com.pollub.db.ConferenceRepository;
import pl.com.pollub.db.entity.Conference;

import java.util.Date;
import java.util.stream.Stream;

@EnableAutoConfiguration
@ComponentScan
public class
ContextConfiguration {

    @Bean
    CommandLineRunner addEntities(ConferenceRepository conferenceRepository) {
        return args -> Stream.of(
                new Conference(1, "Technologie przyszłości", new Date(), new Date(), new Date()),
                new Conference(2, "Technologie dawniej", new Date(), new Date(), new Date()),
                new Conference(3, "Ekonomia w praktyce", new Date(), new Date(), new Date()),
                new Conference(4, "International it meeting", new Date(), new Date(), new Date()),
                new Conference(5, "Technologies of USA", new Date(), new Date(), new Date()),
                new Conference(6, "Nasa technologies", new Date(), new Date(), new Date())
        ).forEach(conferenceRepository::save);
    }
}
