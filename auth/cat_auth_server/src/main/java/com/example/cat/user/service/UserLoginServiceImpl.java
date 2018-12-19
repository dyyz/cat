package com.example.cat.user.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cat.user.UserLoginsRepository;
import com.example.cat.user.entity.UserLogin;
import com.example.cat.user.role.Role;
import com.example.cat.user.role.repository.RoleRepository;

@Service("userLoginDetailService")
@Primary
public class UserLoginServiceImpl implements UserDetailsService, UserLoginService {

	@Autowired
	private UserLoginsRepository userLoginRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserLogin user = userLoginRepository.findByUsername(username);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

	@Autowired PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public void registryUser(UserLogin user) {
		UserLogin newUser = new UserLogin(user.getUsername(), passwordEncoder.encode(user.getPassword()));
		Role role = roleRepository.findByName("ROLE_USER");
		if(role == null) {
			role = new Role("ROLE_USER");
			roleRepository.save(role);
		}
    	Set<Role> roles = new HashSet<>();
    	roles.add(role);
    	newUser.setRoles(roles);
    	userLoginRepository.save(newUser);
	}
}
