package com.example.cat.jwt;

import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.example.cat.Constant;
import com.example.cat.common.JsonUtils;
import com.example.cat.user.entity.BaseUserDetail;
import com.example.cat.user.entity.UserLogin;

public class JwtAccessToken extends JwtAccessTokenConverter {

	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
		
		UserLogin baseUser = ((BaseUserDetail) authentication.getPrincipal()).getUserLogin();
		baseUser.setPassword(null);
		
		defaultOAuth2AccessToken.getAdditionalInformation().put(Constant.USER_INFO, baseUser);
		return super.enhance(defaultOAuth2AccessToken, authentication);
	}
	
	@Override
	public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {
		OAuth2AccessToken oAuth2AccessToken = super.extractAccessToken(value, map);
		convertData(oAuth2AccessToken, map);
		return oAuth2AccessToken;
	}
	
	private void convertData(OAuth2AccessToken accessToken, Map<String, ?> map) {
		accessToken.getAdditionalInformation().put(Constant.USER_INFO, convertUserData(map.get(Constant.USER_INFO)));
	}
	
	private UserLogin convertUserData(Object map) {
		String json = JsonUtils.deserializer(map);
		UserLogin userLogin = JsonUtils.serializable(json, UserLogin.class);
		return userLogin;
	}
	
}
