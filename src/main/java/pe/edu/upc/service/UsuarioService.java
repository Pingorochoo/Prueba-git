package pe.edu.upc.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetailsService;

import pe.edu.upc.entity.Users;

public interface UsuarioService extends UserDetailsService{
	public Users save(@Valid Users user);
	public List<Users>userRoleLess();
}
