package com.example.cat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableZuulProxy
@RestController
@EnableDiscoveryClient
@EnableOAuth2Sso
//@EnableAutoConfiguration(exclude = {
//	    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
//	})
public class CatZuulApplication extends WebSecurityConfigurerAdapter {
	public static void main(String[] args) {
		SpringApplication.run(CatZuulApplication.class, args);
	}
	
	@GetMapping(value = {"/", "/index"})
	public String index() {
		return "这是 GateWay Index";
	}
//	@Autowired
//	private OAuth2RestOperations restTemplate;
//
//	@RequestMapping("/relay")
//	public String relay() {
//	    ResponseEntity<String> response =
//	      restTemplate.getForEntity("http://auth_server:8762/user", String.class);
//	    return "Success! (" + response.getBody() + ")";
//	}
	
	
//	@Bean
//	public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ClientContext context) {
//		return new OAuth2RestTemplate(globalLogin(), context);
//	}
//	
//	@Bean
//	public OAuth2ProtectedResourceDetails globalLogin() {
//		ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
//		details.setId("global/login");
//		details.setClientId("client");
//		details.setClientSecret("client");
//		details.setAccessTokenUri("http://localhost:8762/uaa/oauth/token");
//		details.setScope(Arrays.asList("webclient"));
//		details.setAuthenticationScheme(AuthenticationScheme.query);
//		details.setClientAuthenticationScheme(AuthenticationScheme.form);
//		details.setGrantType("password");
//		return details;
//	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/login", "/uaa/**").permitAll()
			.anyRequest().authenticated();
	}
}
