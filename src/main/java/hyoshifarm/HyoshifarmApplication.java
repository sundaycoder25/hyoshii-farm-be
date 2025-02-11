package hyoshifarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication(scanBasePackages = "hyoshifarm")
@EnableScheduling
public class HyoshifarmApplication {
    public static void main(String[] args) {
        SpringApplication.run(HyoshifarmApplication.class, args);
    }
}
