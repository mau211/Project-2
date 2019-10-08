
package com.example.TravelBuddy.config;

import com.example.TravelBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration annotation is an analog for xml file.
// It will be used to configure our application with security features.
// The @EnableWebSecurity is a marker annotation.
// It allows Spring to find configuration class and automatically apply the class to the global WebSecurity.
@Configuration
@EnableWebSecurity
// WebSecurityConfigurerAdapter is extended to enable HttpSecurity in our app.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    UserService userService;

    @Bean("encoder")
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    //sends out request for token
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    //pushing the hash -- not the actual password, for better security
    private static final String ENCODED_PASSWORD = "$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("user").password(ENCODED_PASSWORD).roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // After adding user info we will now configure our requests. We do this by overriding this method below.
//    This configure method ensures that all requests are authenticated with Http based authentication.
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // antMatchers() allows configuring the HttpSecurity to only be invoked when matching the provided ant pattern
        // we’re using the httpBasic() element to define Basic Authentication
        // http.antMatcher states that this HttpSecurity will only be applicable to URLs that start with /user/

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/auth/admin/*").hasRole("ADMIN")
                .antMatchers("/auth/*").hasAnyRole("ADMIN","USER")
                .and()
                .httpBasic();
    }

//    protected void configure(HttpSecurity http) throws Exception {



//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        auth.inMemoryAuthentication().withUser(users.username("test").password("test").roles("ADMIN"));

//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/signup/**", "/login/**").permitAll()
//                .antMatchers("/user/**", "/profile/**", "/song/**").authenticated()
//                .antMatchers("/role/**").hasRole("DBA")
//                .and()
//                .httpBasic();

//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//    }


//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception{
//
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        auth.inMemoryAuthentication().withUser(users.username("test").password("test").roles("ADMIN"));
//        auth.inMemoryAuthentication().withUser(users.username("dba").password("dba").roles("DBA"));
//    }

}
