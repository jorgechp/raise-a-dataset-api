package cat.udl.eps.raise.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    @Value("${allowed-origins}")
    String[] allowedOrigins;

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers(HttpMethod.GET, "/identity").permitAll()
                .requestMatchers(HttpMethod.POST, "/password").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/missions/check").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/missions/*").hasRole("USER")
                .requestMatchers(HttpMethod.PATCH, "/missions/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/missions/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/compliances/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/users/*").authenticated()
                .requestMatchers(HttpMethod.PUT, "/users/*").authenticated()
                .requestMatchers(HttpMethod.PATCH, "/users/*").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/users/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/repositories/*").hasRole("USER")
                .requestMatchers(HttpMethod.PATCH, "/repositories/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/repositories/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/datasets/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/datasets/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/fAIRPrinciples/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/fAIRPrinciples/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/fAIRPrinciples/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/fAIRPrinciples/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/riskDatasets/*").hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/riskDatasets/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/riskDatasets/*").hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/raiseInstances/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/fAIRPrincipleVerificationInstances/*").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/*/*").denyAll()
                .requestMatchers(HttpMethod.PUT, "/*/*").denyAll()
                .requestMatchers(HttpMethod.PATCH, "/*/*").denyAll()
                .requestMatchers(HttpMethod.DELETE, "/*/*").denyAll()
                .anyRequest().permitAll())
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .cors((cors) -> cors.configurationSource(corsConfigurationSource()))
            .httpBasic(withDefaults()).csrf().disable();
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOriginPatterns(Arrays.asList(allowedOrigins));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

}
