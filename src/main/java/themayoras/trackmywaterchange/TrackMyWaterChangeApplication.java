package themayoras.trackmywaterchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.logging.LoggerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import themayoras.trackmywaterchange.bean.CustomSiteProperties;

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
	
}
