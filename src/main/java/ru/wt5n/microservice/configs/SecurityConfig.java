package ru.wt5n.microservice.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.wt5n.microservice.filters.JwtFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final JwtFilter jwtFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.sessionManagement(s -> s
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeRequests(p -> p
						.antMatchers("/admin/*").hasRole("ADMIN")
						.antMatchers("/user/login").permitAll()
						.antMatchers("/user/*").hasRole("DEVICE"))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
