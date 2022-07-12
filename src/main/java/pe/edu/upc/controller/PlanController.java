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

import pe.edu.upc.entity.Plan;
import pe.edu.upc.service.IPlanService;

@Controller
@RequestMapping("planController")
public class PlanController {

	@Autowired
	private IPlanService planService;
	
	@GetMapping("/new")
	public String newPlan(Model model) {
		model.addAttribute("plan", new Plan());
		return"plan/frmRegister";
	}
	
	@PostMapping("/save")
	public String savePlan(@Valid Plan plan, BindingResult binRes, Model model){
		if(binRes.hasErrors()) {
			model.addAttribute("error", "ocurrio un error al guardar plan ");
			return "plan/frmRegister";
		}else {
			planService.insert(plan);
			model.addAttribute("mensaje", "se guardo correctamente");
			return "redirect:/planController/new";
		}  
	}
	
	@GetMapping("/toList")
	public String toListPlan(Model model) {
		try {
			model.addAttribute("listaPlan", planService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getCause());
		}
		return "plan/frmList";
	}
	@RequestMapping("/delete")
	public String deletePlan(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if(id!=null&&id>0) {
				planService.delete(id);
				model.put("listaPlan", planService.list());//preguntar a la miss 
			} 
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return "redirect:/planController/toList";  
	}
	@RequestMapping("/edit/{id}")
	public String goUpdatePlan(@PathVariable int id, Model model) {
		Optional<Plan> plan =planService.listId(id);
		model.addAttribute("plan", plan.get());
		return "plan/frmUpdate";
	}
	@RequestMapping("/update")
	public String updatePsicologo(Plan plan) {
		planService.update(plan);//hay un problema cuando el id es cero
		return "redirect:/planController/toList";
	}
}
