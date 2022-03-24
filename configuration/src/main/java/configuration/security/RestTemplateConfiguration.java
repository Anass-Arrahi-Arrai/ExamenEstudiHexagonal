package configuration.security;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"application", "persistanceAdapter", "webAdapter", "configuration.security"})
public class RestTemplateConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(RestTemplateConfiguration.class, args);
    }
}
