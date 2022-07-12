package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.entity.Role;
import pe.edu.upc.entity.Users;
import pe.edu.upc.service.IRolService;
import pe.edu.upc.service.UsuarioService;


@Controller
@RequestMapping("/registro")
public class RegistroController {
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private IRolService IRolService;
	
	@ModelAttribute("usuario")
	public Users retornarNuevoUsuario() {
		return new Users();
	}

	@GetMapping
	public String newPaciente(Model model) {
		return "/registro";
	}
	
	@PostMapping
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") Users user) {
		Role rol= IRolService.listId(Long.valueOf(4)).get();
		user.añadirRol(rol);
		usuarioService.save(user);
		return "redirect:/registro?exito";
	}
}
