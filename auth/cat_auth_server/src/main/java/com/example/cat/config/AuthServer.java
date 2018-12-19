package com.example.cat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@EnableAuthorizationServer
@Configuration
public class AuthServer extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private PasswordEncoder passwordEncode;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
			.tokenKeyAccess("permitAll()")
			.checkTokenAccess("permitAll()")
			.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.inMemory().withClient("client")
				.secret(passwordEncode.encode("client"))
				.authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code")
				.scopes("webclient", "mobileclient")
				.autoApprove(true)
				.authorities("WRIGTH_READ")
//				.redirectUris("https://www.baidu.com/")
			.and()
			.inMemory().withClient("client1")
				.secret(passwordEncode.encode("client1"))
				.authorizedGrantTypes("refresh_token", "password")
				.scopes("webclient", "mobileclient")
				.autoApprove(true);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.authenticationManager(authenticationManager)
			.accessTokenConverter(jwtAccessTokenConverter())
			.reuseRefreshTokens(false);
//			.userDetailsService(userDetailService);
	}
	
	/**
	 * 使用非对称加密算法对 Token 进行签名
	 * @return
	 */
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		KeyStoreKeyFactory keyStoreKeyFactory = 
			new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "mypass".toCharArray());
		converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));
//		converter.setVerifierKey(getPubKey());
//		converter.setSigningKey("springcloud123");
		
		return converter;
	}
	
//	private String getPubKey() {
//		Resource resource = new ClassPathResource("pubkey.txt");
//		try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
//			return br.lines().collect(Collectors.joining("\n"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			return null;
//		}
//	}
}
