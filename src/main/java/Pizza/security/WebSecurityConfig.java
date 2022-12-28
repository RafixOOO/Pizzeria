package Pizza.security;


import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/Panel").hasAnyAuthority("Role_Admin", "Role_Staff")
                .antMatchers("/Pracownicy").hasAuthority("Role_Admin")
                .antMatchers("/Menu").hasAuthority("Role_Admin")
                .antMatchers("/Info").hasAuthority("Role_Admin")
                .antMatchers("/Password").hasAuthority("Role_Admin")
                .antMatchers("/Zamowienia").hasAnyAuthority("Role_Staff", "Role_Admin")
                .anyRequest().permitAll()
                .and()
            .formLogin()
                .loginPage("/Login")
                .permitAll();
    }

    @Autowired
    DataSource dataSource;
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select username,password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, role from user_roles where username=?");

        
    }
    
}

