package com.school.school.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import net.bytebuddy.build.Plugin.Engine.Source.InMemory;

@Configuration
public class WebSecurityConfig {

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
    return http.csrf().disable()
        .authorizeHttpRequests().anyRequest()
        .fullyAuthenticated().and()
        .httpBasic().and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().build();

  }

  // @Bean
  // UserDetailsService userDetailsService() {
  // InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
  // // return
  // manager.createUser(User.withUsername("user").password("123455").roles("USER").build());

  // }
}
