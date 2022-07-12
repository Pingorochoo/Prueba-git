package pe.edu.upc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.entity.Boleta;
import pe.edu.upc.service.IBoletaService;

@Controller
@RequestMapping("boletaController")
public class BoletaController{
	@Autowired
	private IBoletaService boletaService;
	
	@GetMapping("/toList")
	public String toListPlan(Model model) {
		try {
			model.addAttribute("listaBoleta", boletaService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getCause());
		}
		return "boleta/frmList";
	}
	@RequestMapping("/edit/{id}")
	public String editarBoleta(@PathVariable int id, Model model) {
		Optional<Boleta> plan =boletaService.listId(id);
		model.addAttribute("plan", plan.get());
		return "boleta/frmUpdate";
	}
	@RequestMapping("/update")
	public String updateBoleta(Boleta boleta) {
		boletaService.update(boleta);//hay un problema cuando el id es cero
		return "redirect:/boletaController/toList";
	}
}