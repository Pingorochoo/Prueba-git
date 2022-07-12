package pe.edu.upc.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.upc.entity.Psicologo;
import pe.edu.upc.service.IPsicologoService;
import pe.edu.upc.service.UsuarioService;

@Controller
@RequestMapping("psicologoController")
public class PsicologoController {

	@Autowired
	private IPsicologoService psicologoService;
	
	@Autowired
	private UsuarioService usuarioService;
	@GetMapping("/new")
	public String newPsicologo(Model model) {
		model.addAttribute("psicologo", new Psicologo());
		model.addAttribute("listaUsuariosSinRoles",usuarioService.userRoleLess());
		return "psicologo/frmRegister";
	}

	@PostMapping("/save")
	public String savePsicologo(@Valid Psicologo psicologo, BindingResult binRes, Model model) {
		if (binRes.hasErrors()) {
			model.addAttribute("error", "ocurrio un error: ");
			return "psicologo/frmRegister";
		} else {
			psicologoService.insert(psicologo);
			model.addAttribute("mensaje", "se guardo correctamente");
			return "redirect:/psicologoController/new";
		}
	}

	@GetMapping("/toList")
	public String toListPsicologo(Model model) {
		try {
			model.addAttribute("listaPsicologo", psicologoService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getCause());
		}
		return "psicologo/frmList";
	}

	@RequestMapping("/delete")
	public String deletePsicologo(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if(id!=null&&id>0) {
				psicologoService.delete(id);
				model.put("listaPsicologo", psicologoService.list());//preguntar a la miss 
			} 
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return "psicologo/frmList";  
	}

	@RequestMapping("goUpdate/{id}")
	public String goUpdatePsicologo(@PathVariable int id, Model model) {
		Optional<Psicologo> psicologo =psicologoService.listId(id);
		model.addAttribute("psa", psicologo.get());
		return "psicologo/frmUpdate";
	}

	@RequestMapping("update")
	public String updatePsicologo(Psicologo psicologo) {
		psicologoService.update(psicologo);
		return "redirect:/psicologoController/toList";
	}
	@GetMapping("/toListContratosVencer")
	public String toListContratosVencerPsicologo(Model model) {
		try {
			model.addAttribute("listaPsicologo", psicologoService.listContratosVencer());
		} catch (Exception e) {
			model.addAttribute("error", e.getCause());
		}
		return "psicologo/frmListContratosVencer";
	}
	@GetMapping("/toListContratosVencerFecha")
	public String toListContratosVencerFechaPsicologo(Model model, int dias) {
		try {
			model.addAttribute("listaPsicologo", psicologoService.listConsultaContratosVencer(dias));
		} catch (Exception e) {
			model.addAttribute("error", e.getCause());
		}
		return "psicologo/frmListContratosVencer";
	}
}
