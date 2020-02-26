package mk.ukim.finki.emt.sports_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ServletComponentScan
@SpringBootApplication
public class SportsShopApplication {

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    public static void main(String[] args) {
        SpringApplication.run(SportsShopApplication.class, args);
    }

}
