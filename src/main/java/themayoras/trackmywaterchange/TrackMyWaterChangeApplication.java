package themayoras.trackmywaterchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import themayoras.trackmywaterchange.bean.CustomSiteProperties;
import themayoras.trackmywaterchange.security.UserDetailsServiceImpl;

@SpringBootApplication
@ComponentScan
@EnableJpaRepositories
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
}
