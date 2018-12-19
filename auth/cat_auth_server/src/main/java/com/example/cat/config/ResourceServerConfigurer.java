//package com.example.cat.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//
//@Configuration
//@EnableResourceServer
//@Order(3)
//public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {
//
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
////		http
////			.formLogin().loginPage("/login").loginProcessingUrl("/login").permitAll()
////			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
////			.and()
////			.authorizeRequests()
////				.antMatchers("/", "/auth/**", "/oauth/**", "oauth/authorize", "/index", "/register", "/webjars/**").permitAll()
////			.and().csrf().disable().exceptionHandling().authenticationEntryPoint((req, res, authExp)->res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
//		http.antMatcher("/user").authorizeRequests().anyRequest().authenticated().and().csrf().disable();
//	}
//	
//}
