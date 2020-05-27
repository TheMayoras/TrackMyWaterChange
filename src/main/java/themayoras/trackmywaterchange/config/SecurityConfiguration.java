package themayoras.trackmywaterchange.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("authProviderBean")
    private DaoAuthenticationProvider authProvider;

    //@formatter:off
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.userDetailsService(userDetailsService);

        auth.authenticationProvider(authProvider);

        auth.jdbcAuthentication()
            .passwordEncoder(passwordEncoder)
            .dataSource(dataSource)
		        .usersByUsernameQuery("SELECT username, password, true from users WHERE username=?")
		        .authoritiesByUsernameQuery("SELECT username, role FROM users_roles WHERE username=?");

    }
    //@formatter:on

    // @formatter:off
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
		            .antMatchers("/home", "/home/**", "/tank", "/tank/**", "/current-user").hasRole("USER")
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
            .headers()
            .and()
            .logout()
                .logoutSuccessUrl("/?logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
            .and()
                .requiresChannel().antMatchers("/**").requiresSecure()
            .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).maximumSessions(1)
                .and()
            .and()
                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            .and()
                .exceptionHandling().accessDeniedPage("/user/access-denied")
            .and()
                .userDetailsService(userDetailsService);

    }
    // @autoformatter:on

	//@formatter:on


}
