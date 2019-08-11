package themayoras.trackmywaterchange;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import themayoras.trackmywaterchange.security.CustomAuthenticationProvider;
import themayoras.trackmywaterchange.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    //@formatter:off
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.userDetailsService(userDetailsService);
	
        auth.authenticationProvider(authenticationProvider());
	
        auth.jdbcAuthentication()
            .passwordEncoder(passwordEncoder)
            .dataSource(dataSource)
		        .usersByUsernameQuery("SELECT username, password, true from users WHERE username=?")
		        .authoritiesByUsernameQuery("SELECT username, role FROM users_roles WHERE username=?");
	
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
		            .antMatchers("/home", "/home/**", "/tank", "/tank/**").hasRole("USER")
		            .antMatchers("/admin/**").hasRole("ADMIN")
		            .antMatchers("/user/register").permitAll()
		            .antMatchers("/user/access-denied").permitAll()
		        .and()
		        .formLogin()
		            .loginPage("/user/login")
		            .failureUrl("/user/login?error")
            		.permitAll()
            		.defaultSuccessUrl("/home")
            .and()
            .logout()
                .logoutSuccessUrl("/?logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)               
            .and()
            .exceptionHandling().accessDeniedPage("/user/access-denied")
            .and()
            .userDetailsService(userDetailsService);
              
    }
   
    
	//@formatter:on

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsServiceBean() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final CustomAuthenticationProvider customAutheticationProvider = new CustomAuthenticationProvider(
                userDetailsServiceBean());
        customAutheticationProvider.setUserDetailsService(this.userDetailsService);
        customAutheticationProvider.setPasswordEncoder(passwordEncoder());

        return customAutheticationProvider;
    }

}
