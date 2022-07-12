package pe.edu.upc.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.upc.entity.Paciente;
import pe.edu.upc.service.IPacienteService;
import pe.edu.upc.service.UsuarioService;

@Controller
@RequestMapping("pacienteController")
public class PacienteController {

	@Autowired
	private IPacienteService pacienteService;
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/new")
	public String newPaciente(Model model) {
		model.addAttribute("paciente", new Paciente());
		model.addAttribute("listaUsuariosSinRoles", usuarioService.userRoleLess());
		return "paciente/frmRegister";
	}

	@PostMapping("/save")
	public String savePaciente(@Valid Paciente paciente, BindingResult binRes, Model model) {
		if (binRes.hasErrors()) {
			model.addAttribute("error", "ocurrio un error: ");
			return "paciente/frmRegister";
		} else {
			pacienteService.insert(paciente);
			model.addAttribute("mensaje", "se guardo correctamente");
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserDetails userDetails = null;
			if (principal instanceof UserDetails) {
				userDetails = (UserDetails) principal;
			}
			String userName = userDetails.getUsername();
			if (userName.equals("admin"))
				return "redirect:/pacienteController/new";
			else 
				return "redirect:/logout";
		}
	}

	@GetMapping("/toList")
	public String toListPaciente(Model model) {
		try {
			model.addAttribute("listaPaciente", pacienteService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getCause());
		}
		return "paciente/frmList";
	}

	@RequestMapping("/delete")
	public String deletePaciente(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				pacienteService.delete(id);
				model.put("listaPaciente", pacienteService.list());// preguntar a la miss
			}
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return "paciente/frmList";
	}

	@RequestMapping("goUpdate/{id}")
	public String goUpdatePaciente(@PathVariable int id, Model model) {
		Optional<Paciente> paciente = pacienteService.listId(id);
		model.addAttribute("psa", paciente.get());
		return "paciente/frmUpdate";
	}
	
	@RequestMapping("editProfile")
	public String editarPerfil( Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		String userName = userDetails.getUsername();
		//Optional<Paciente> paciente = pacienteService.listId(id);
		//model.addAttribute("psa", paciente.get());
		return "paciente/frmUpdate";
	}

	@RequestMapping("update")
	public String updatePaciente(Paciente paciente) {
		pacienteService.update(paciente);
		return "redirect:/pacienteController/toList";
	}

}
