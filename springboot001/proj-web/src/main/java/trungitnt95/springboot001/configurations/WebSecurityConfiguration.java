package trungitnt95.springboot001.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration {

    @Value("${app.security.disabled:false}")
    private boolean securityDisabled;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HttpSecurity httpSecurity = http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
        if (!securityDisabled) {
            // TODO: perform security check with JWT
        } else {
            httpSecurity.headers().frameOptions().disable();
            httpSecurity.authorizeHttpRequests()
                    .requestMatchers("/**").permitAll();
        }

        return http.build();
    }
}
