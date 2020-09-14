package org.jordyn.vsav;

import java.util.Arrays;

import org.jordyn.vsav.security.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * The application's entry point
 * 
 * @author jordyn
 */
@EnableCaching
@SpringBootApplication
@EnableAutoConfiguration
public class App {
	
	@Autowired
	private Environment env;

	public static void main(final String[] args) {
		SpringApplication.run(App.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(final HttpSecurity http) throws Exception {
			http.csrf().disable()
			.cors().and()
			.addFilterBefore(new JWTAuthorizationFilter(env), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/GenerateAccessToken/**", "/RetrieveVideos/**", "/RetrieveMatches/**").permitAll()
			.antMatchers(HttpMethod.OPTIONS, "/**", "/*").permitAll()
			.antMatchers(HttpMethod.GET, "/GetToken/**", "/RetrieveUniquePlayers/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}

		@Bean
		CorsConfigurationSource corsConfigurationSource() {
			CorsConfiguration corsConfig = new CorsConfiguration();
			corsConfig.setAllowedOrigins(Arrays.asList("https://vsav.video", "http://localhost:4200"));
			corsConfig.setAllowedMethods(Arrays.asList("*"));
			corsConfig.setAllowedHeaders(Arrays.asList("*"));
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", corsConfig);
			return source;
		}

	}

}
