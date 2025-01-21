package fr.eni.lodo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/", "/home").permitAll()
                        .requestMatchers(HttpMethod.POST, "/client/ajouter").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.POST, "/client/modifier/**").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.POST, "/jeu/ajouter").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.POST, "/jeu/modifier/**").hasRole("EMPLOYER")

                        .requestMatchers(HttpMethod.GET, "/client/ajouter").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.GET, "/client/modifier/**").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.GET, "/client/supprimer/**").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.GET, "/jeu/ajouter").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.GET, "/jeu/modifier/**").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.GET, "/jeu/supprimer/**").hasRole("EMPLOYER")

                        .requestMatchers(HttpMethod.GET, "/client/lister").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.GET, "/jeu/lister").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/jeu/lister").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.GET, "/jeu/{id}").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/jeu/{id}").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.GET, "/client/{id}").hasRole("EMPLOYER")

                        .requestMatchers(HttpMethod.GET, "/exemplaire/ajouter/{id}").hasRole("EMPLOYER")

                        .requestMatchers(HttpMethod.GET, "/").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/").hasRole("EMPLOYER")
                        .anyRequest().denyAll()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("client")
                        .password("azerty")
                        .roles("CLIENT")
                        .build();
        UserDetails user1 =
                User.withDefaultPasswordEncoder()
                        .username("employer")
                        .password("azerty")
                        .roles("EMPLOYER")
                        .build();

        return new InMemoryUserDetailsManager(user, user1);
    }
}