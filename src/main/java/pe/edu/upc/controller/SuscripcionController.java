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

import pe.edu.upc.entity.Boleta;
import pe.edu.upc.entity.Suscripcion;
import pe.edu.upc.service.IPacienteService;
import pe.edu.upc.service.IPlanService;
import pe.edu.upc.service.ISuscripcionService;

@Controller
@RequestMapping("suscripcionController")
public class SuscripcionController {
	@Autowired
	private ISuscripcionService suscripcionService;
	@Autowired
	private IPlanService planService;
	@Autowired
	private IPacienteService pacienteService;

	@GetMapping("/new")
	public String newSuscripcion(Model model) {
		model.addAttribute("suscripcion", new Suscripcion(new Boleta()));
		model.addAttribute("listaPlanes", planService.list());
		model.addAttribute("listaPacientes", pacienteService.listNoSuscritos());
		return "suscripcion/frmRegister";
	}

	@PostMapping("/save")
	public String saveSuscripcion(@Valid Suscripcion suscripcion, BindingResult binRes, Model model) {
		if (binRes.hasErrors()) {
			model.addAttribute("error", "ocurrio un error al guardar suscripcion: ");
		} else {
			suscripcionService.insert(suscripcion);
			model.addAttribute("mensaje", "se guardo correctamente");
		}
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		String userName = userDetails.getUsername();
		if (userName.equals("admin"))
			return "redirect:/suscripcionController/new";
		else 
			return "redirect:/logout";
	}

	@RequestMapping("/delete")
	public String deleteSuscripcion(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				suscripcionService.delete(id);
				model.put("listaSuscripcion", suscripcionService.list());// preguntar a la miss
			}
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return "redirect:/suscripcionController/toList";
	}

	@GetMapping("/toList")
	public String toListSucripcion(@RequestParam Map<String, Object> params, Model model) {
		try {
			model.addAttribute("listaSuscripcion", suscripcionService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getCause());
		}
		return "suscripcion/frmList";
	}

	@RequestMapping("/edit/{id}")
	public String editSuscripcion(@PathVariable int id, Model model) {
		Optional<Suscripcion> suscripcion = suscripcionService.listId(id);
		model.addAttribute("suscripcionEditar", suscripcion.get());
		model.addAttribute("listaPlanes", planService.list());
		return "suscripcion/frmUpdate";
	}

	@RequestMapping("/update")
	public String updateSuscripcion(Suscripcion suscripcion) {
		suscripcionService.update(suscripcion);
		return "redirect:/suscripcionController/toList";
	}
}