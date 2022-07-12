package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.entity.Users;
import pe.edu.upc.service.UsuarioService;

@Controller
@RequestMapping("usuarioController")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@ModelAttribute("usuario")
	public Users retornarNuevoUsuarioRegistroDTO() {
		return new Users();
	}
	@GetMapping
	public String mostrarFormularioDeRegistro() {
		return "registro";
	}
}
