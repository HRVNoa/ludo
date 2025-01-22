package fr.eni.lodo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/", "/home").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/client/ajouter").hasRole("EMPLOYER")
//                        .requestMatchers(HttpMethod.POST, "/client/modifier/**").hasRole("EMPLOYER")
//                        .requestMatchers(HttpMethod.POST, "/jeu/ajouter").hasRole("EMPLOYER")
//                        .requestMatchers(HttpMethod.POST, "/jeu/modifier/**").hasRole("EMPLOYER")
//                        .requestMatchers(HttpMethod.POST, "/exemplaire/ajouter/{id}").hasRole("EMPLOYER")
//                        .requestMatchers(HttpMethod.POST, "/exemplaire/modifier/{id}").hasRole("EMPLOYER")
//
//                        .requestMatchers(HttpMethod.GET, "/client/ajouter").hasRole("EMPLOYER")
//                        .requestMatchers(HttpMethod.GET, "/client/modifier/**").hasRole("EMPLOYER")
//                        .requestMatchers(HttpMethod.GET, "/client/supprimer/**").hasRole("EMPLOYER")
//                        .requestMatchers(HttpMethod.GET, "/jeu/ajouter").hasRole("EMPLOYER")
//                        .requestMatchers(HttpMethod.GET, "/jeu/modifier/**").hasRole("EMPLOYER")
//                        .requestMatchers(HttpMethod.GET, "/jeu/supprimer/**").hasRole("EMPLOYER")
//
//                        .requestMatchers(HttpMethod.GET, "/client/lister").hasRole("EMPLOYER")
//                        .requestMatchers(HttpMethod.GET, "/jeu/lister").authenticated()
//                        .requestMatchers(HttpMethod.GET, "/jeu/{id}").authenticated()
//                        .requestMatchers(HttpMethod.GET, "/client/{id}").hasRole("EMPLOYER")
//
//                        .requestMatchers(HttpMethod.GET, "/exemplaire/ajouter/{id}").hasRole("EMPLOYER")
//                        .requestMatchers(HttpMethod.GET, "/exemplaire/modifier/{id}").hasRole("EMPLOYER")
//                        .requestMatchers(HttpMethod.GET, "/exemplaire/supprimer/{id}").hasRole("EMPLOYER")
//
//                        .requestMatchers(HttpMethod.GET, "/").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());  // Ici, Spring Security appelle matches() pour vous.

        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utiliser un encoder sécurisé
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("client")
//                        .password("azerty")
//                        .roles("CLIENT")
//                        .build();
//        UserDetails user1 =
//                User.withDefaultPasswordEncoder()
//                        .username("employer")
//                        .password("azerty")
//                        .roles("EMPLOYER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user, user1);
//    }
}