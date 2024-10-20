package com.project.sanayiApp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.project.sanayiApp.business.concretes.UserDetailsManager;
import com.project.sanayiApp.security.JwtAuthenticationEntryPoint;
import com.project.sanayiApp.security.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
	
	private UserDetailsManager userDetailsManager;
	
	private JwtAuthenticationEntryPoint handler;
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	/*
	 * @Bean(BeanIds.AUTHENTICATION_MANAGER) public AuthenticationManager
	 * authenticationManagerBean() throws Exception{ return
	 * authenticationManagerBean(); }
	 */
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	
	
	  @Bean public AuthenticationManager
	  authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception { 
		  return authenticationConfiguration.getAuthenticationManager(); 
	}
	 
	
	/*
	 * @Bean public void configure(AuthenticationManagerBuilder
	 * authenticationManagerBuilder) throws Exception{
	 * authenticationManagerBuilder.userDetailsService(userDetailsManager).
	 * passwordEncoder(passwordEncoder()); }
	 */
	
	@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
	
	
	}
	
	/*
	 * @Bean public void configure(HttpSecurity httpSecurity) throws Exception{
	 * httpSecurity .cors() .and() .csrf().disable()
	 * .exceptionHandling().authenticationEntryPoint(handler).and()
	 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
	 * and() .authorizeRequests() .requestMatchers("/auth/**") .permitAll()
	 * .anyRequest().authenticated();
	 * 
	 * httpSecurity.addFilterBefore(jwtAuthenticationFilter(),
	 * UsernamePasswordAuthenticationFilter.class); }
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(java.util.Arrays.asList("https://example.com"));
		configuration.setAllowedMethods(java.util.Arrays.asList("GET","POST"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	
	
	  @Bean
	  public SecurityFilterChain filterChain(HttpSecurity httpSecurity)
	  throws Exception { 
		  httpSecurity
		  .cors(cors -> cors.configurationSource(corsConfigurationSource()))
		  .csrf(csrf -> csrf.disable()) // csrf yapılandırmasını fonksiyonel yapıyla yapın
	        .exceptionHandling(exception -> exception
	            .authenticationEntryPoint(handler)) // exceptionHandling fonksiyonel yapıda
	        .sessionManagement(session -> session
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // session management
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers(HttpMethod.GET, "/products").permitAll()
	            .requestMatchers("/auth/**").permitAll()
	            .anyRequest().authenticated());
		  
		
		  httpSecurity.addFilterBefore(jwtAuthenticationFilter(),
		  UsernamePasswordAuthenticationFilter.class); 
		  return httpSecurity.build(); 
	}
	 

}
