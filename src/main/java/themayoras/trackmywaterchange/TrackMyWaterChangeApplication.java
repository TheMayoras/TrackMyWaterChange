package themayoras.trackmywaterchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.logging.LoggerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import themayoras.trackmywaterchange.bean.CustomSiteProperties;
import themayoras.trackmywaterchange.security.CustomAuthenticationProvider;
import themayoras.trackmywaterchange.security.UserDetailsServiceImpl;

@SpringBootApplication
@ComponentScan
@EnableJpaRepositories
@EnableAspectJAutoProxy
public class TrackMyWaterChangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrackMyWaterChangeApplication.class, args);
    }

    @Bean
    @Scope("singleton")
    public CustomSiteProperties customSiteProperties() {
        return new CustomSiteProperties();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsServiceBean() {
        return new UserDetailsServiceImpl();
    }

    @Autowired
    @Bean("authProviderBean")
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        final CustomAuthenticationProvider customAutheticationProvider = new CustomAuthenticationProvider(
                userDetailsService);
        customAutheticationProvider.setUserDetailsService(userDetailsService);
        customAutheticationProvider.setPasswordEncoder(passwordEncoder);

        return customAutheticationProvider;
    }
}
