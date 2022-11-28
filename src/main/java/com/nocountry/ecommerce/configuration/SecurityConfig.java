package com.nocountry.ecommerce.configuration;

import com.nocountry.ecommerce.common.security.filter.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.request.RequestContextListener;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   private final UserDetailsService userDetailService;

   private final JwtRequestFilter jwtRequestFilter;

   private final PasswordEncoder encoder;

   @Override
   @Bean
   public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
   }

   @Bean
   public RequestContextListener listener() { return new RequestContextListener(); }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailService).passwordEncoder(encoder);
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {

      http.csrf().disable().authorizeRequests()
         .anyRequest().permitAll()
         .and().exceptionHandling()
         .and().sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

      http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
   }


}