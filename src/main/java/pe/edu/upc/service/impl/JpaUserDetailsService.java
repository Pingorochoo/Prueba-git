package pe.edu.upc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Role;
import pe.edu.upc.entity.Users;
import pe.edu.upc.repository.UserRepository;
import pe.edu.upc.service.UsuarioService;

@Service
public class JpaUserDetailsService implements UsuarioService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByUsername(username);

//		if(user == null) {
//			throw new UsernameNotFoundException("Usuario o password inválidos gaaaaa");
//		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getNombre()));
		}

		return new User(user.getUsername(), user.getPassword(), authorities);
	}

	@Override
	public Users save(@Valid Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public List<Users> userRoleLess() {
		List<Users> usuariosSinRol = new ArrayList<Users>();
		for (Users user : userRepository.findAll()) {
			List<Role> rlst=(List<Role>) user.getRoles();
			if (rlst.size() == 1&&rlst.get(0).getId()==4)
				usuariosSinRol.add(user);
		}
		return usuariosSinRol;
	}
}
