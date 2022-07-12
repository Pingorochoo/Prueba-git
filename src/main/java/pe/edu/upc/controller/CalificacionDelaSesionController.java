package pe.edu.upc.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.upc.entity.CalificacionDeLaSesion;
import pe.edu.upc.entity.SesionTerapia;
import pe.edu.upc.service.ICalificacionService;
import pe.edu.upc.service.ISesionTerapiaService;

@Controller
@RequestMapping("calificacionDelaSesionController")
public class CalificacionDelaSesionController {
	@Autowired
	private ICalificacionService calificacionService;
	@Autowired
	private ISesionTerapiaService sesionTerapiaService;
	
	@GetMapping("/new")
	public String newCalificacion(Model model) {
		model.addAttribute("listaSesionesPorPaciente",sesionTerapiaService.listNoCalidicadasPorPaciente());
		model.addAttribute("sesion", new SesionTerapia(new CalificacionDeLaSesion()));
		return"calificacionDeLaSesion/frmRegister";
	}
	
	@PostMapping("/save")
	public String saveCalificacion(@Valid CalificacionDeLaSesion calificacionDeLaSesion, BindingResult binRes, Model model){
		if(binRes.hasErrors()) {
			model.addAttribute("error", "ocurrio un error: ");
			return "calificacionDeLaSesion/frmRegister";
		}else {
			calificacionService.insert(calificacionDeLaSesion);
			model.addAttribute("mensaje", "se guardo correctamente");
			return "redirect:/calificacionDelaSesionController/new";
		}  
	}
	
	@RequestMapping("/ac")
	public String autoCompletar(){
		calificacionService.autocompletar(5);
			return "redirect:/calificacionDelaSesionController/toList";
	}
	
	@RequestMapping("/delete")
	public String deleteCalificacion(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if(id!=null&&id>0) {
				calificacionService.delete(id);
				model.put("listaCalificaciones", calificacionService.list());//preguntar a la miss 
			} 
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return "redirect:/calificacionDelaSesionController/toList";  
	}
	@GetMapping("/toList")
	public String toListCalificacion(@RequestParam Map<String, Object> params,Model model) {
		try {
			int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
			PageRequest pageRequest = PageRequest.of(page, 5);
			Page<CalificacionDeLaSesion> pageTest = calificacionService.getAll(pageRequest);
			int totalPage = pageTest.getTotalPages(); 
			if(totalPage > 0) {
				List<Integer> numeroDePaginas = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
				model.addAttribute("numeroDePaginas", numeroDePaginas);
			}
			model.addAttribute("current", page + 1);
			model.addAttribute("next", page + 2);
			model.addAttribute("prev", page);
			model.addAttribute("last", totalPage);
			model.addAttribute("listaCalificaciones", pageTest.getContent());
		} catch (Exception e) {
			model.addAttribute("error", e.getCause());
		}
		return "calificacionDeLaSesion/frmList";
	}
	
	@RequestMapping("/goUpdate/{id}")
	public String goUpdateCalificacion(@PathVariable int id, Model model) {
		Optional<CalificacionDeLaSesion> calificacion =calificacionService.listId(id);
		model.addAttribute("psa", calificacion.get());
		return "calificacionDeLaSesion/frmUpdate";
	}
	@RequestMapping("/update")
	public String updateCalificacion(CalificacionDeLaSesion calificacionDeLaSesion) {
		calificacionService.update(calificacionDeLaSesion);
		return "redirect:/calificacionDelaSesionController/toList";
	}
	
	
}
