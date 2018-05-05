package hospital.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by Catalysts on 10/9/2015.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public ShaPasswordEncoder passwordEncoder() {
        return new ShaPasswordEncoder();
    }
    @Autowired
    private DataSource dataSource;

    @Autowired
    LoginSuccessHandler loginSuccessHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery("select user_roles.username, role from user_roles inner join users on users.username = user_roles.username where user_roles.username = ?")
                .rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/login*").permitAll()
                .antMatchers("/administrator/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/secretary/**").access("hasRole('ROLE_SECRETARY')")
                .antMatchers("/doctor/**").access("hasRole('ROLE_DOCTOR')")
                //.antMatchers("/logout","/user","/books","/booksByName","/booksByGenre","/booksByAuthor","/sellBook").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
                //.anyRequest().access("hasRole('ROLE_ADMIN')")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(loginSuccessHandler)
                .and()
                .logout()
                .permitAll();
    }
}