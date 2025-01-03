package LaserGame.Security.Configuration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
@EnableJpaRepositories(basePackages = "LaserGame.Repository")
@EntityScan(basePackages = "LaserGame.Entities")
public class JpaConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory(EntityManagerFactory emf) {
        return emf;
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthoritiesClaimName("preferred_username");
        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return converter;
    }
}

