package pl.com.pollub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

//@PropertySource(value = {"file:C:/Projects/config/config.properties"}) // Final properties config
@SpringBootApplication(scanBasePackages = {"pl.com.pollub"})
@EnableScheduling
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
