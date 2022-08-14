package com.bourg.restservice.restservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication()
			.withUser("Zack")
			.password("aayush")
			//.roles("admin_role", "USER")
			.authorities(new SimpleGrantedAuthority("FOO_READ_PRIVILEGE"))
			;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.authorizeRequests()
		// .antMatchers("/", "/v1/**").permitAll()
		// .anyRequest().authenticated().and().logout().permitAll();

		http.httpBasic()
		.and()
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin().disable();

	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {

		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);

	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
