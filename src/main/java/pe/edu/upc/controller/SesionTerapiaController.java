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

import pe.edu.upc.entity.SesionTerapia;
import pe.edu.upc.service.IDocumentoService;
import pe.edu.upc.service.IPacienteService;
import pe.edu.upc.service.IPsicologoService;
import pe.edu.upc.service.ISesionTerapiaService;
import pe.edu.upc.service.ITestService;

@Controller
@RequestMapping("sesionTerapiaController")
public class SesionTerapiaController {

	@Autowired
	private ISesionTerapiaService sesionTerapiaService;
	@Autowired
	private IPacienteService pacienteService;
	@Autowired
	private IPsicologoService psicologoService;
	@Autowired
	private IDocumentoService documentoService;
	@Autowired
	private ITestService testService;
	@GetMapping("/new")
	public String newSesionTerapia(Model model) {
		
		model.addAttribute("sesionTerapia", new SesionTerapia());
		model.addAttribute("listaPaciente", pacienteService.list());
		model.addAttribute("listaPsicologo", psicologoService.list());
		
		model.addAttribute("listaDocumentosNoInsertados", documentoService.listNoInsertados());
		model.addAttribute("listaTestNoInsertados", testService.listNoInsertados());
	
		return"sesionTerapia/frmRegister";
	}
	@PostMapping("/save")
	public String saveSesionTerapia(@Valid SesionTerapia sesionTerapia, BindingResult binRes, Model model){
		if(binRes.hasErrors()) {
			model.addAttribute("error", "ocurrio un error: ");
			return "sesionTerapia/frmRegister";
		}else {
			sesionTerapiaService.insert(sesionTerapia);
			model.addAttribute("mensaje", "se guardo correctamente");
			return "redirect:/sesionTerapiaController/new";
		}  
	}
	@PostMapping("/saveCalificacion")
	public String saveCalificacionSesionTerapia(@Valid SesionTerapia sesionTerapia, BindingResult binRes, Model model){
		if(binRes.hasErrors()) {
			model.addAttribute("error", "ocurrio un error: ");
			return "sesionTerapia/frmRegister";
		}else {
			sesionTerapiaService.insertCalificacion(sesionTerapia);
			model.addAttribute("mensaje", "se guardo correctamente");
			return "redirect:/sesionTerapiaController/new";
		}  
	}
	
	@GetMapping("/toList")
	public String toListSesionTerapia(Model model) {
		try {
			model.addAttribute("listaSesionTerapia", sesionTerapiaService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getCause());
		}
		return "sesionTerapia/frmList";
	}
	@RequestMapping("/delete")
	public String deleteSesionTerapia(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if(id!=null&&id>0) {
				sesionTerapiaService.delete(id);//sesionTerapiaRepository.deleteById(idSesion);
				model.put("listaSesionTerapia", sesionTerapiaService.list());//preguntar a la miss 
			} 
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return "sesionTerapia/frmList";  
	}
	@RequestMapping("goUpdate/{id}")
	public String goUpdateSesionTerapia(@PathVariable int id, Model model) {
			Optional<SesionTerapia> sesionTerapia =sesionTerapiaService.listId(id);
			model.addAttribute("sesion", sesionTerapia.get());
			model.addAttribute("listaPaciente", pacienteService.list());
			model.addAttribute("listaPsicologo", psicologoService.list());
			
			model.addAttribute("listaDocumentos", documentoService.list());
			model.addAttribute("listaTest", testService.list());
			return "sesionTerapia/frmUpdate";
	}
	@RequestMapping("update")
	public String updatePsicologo(SesionTerapia sesionTerapia) {
		sesionTerapiaService.update(sesionTerapia);
		return "redirect:/sesionTerapiaController/toList";
	}
	@RequestMapping("/ac")
	public String autoCompletar(){
			testService.autocompletar(1);
			//calificacionSerivce.autocompletar();
			documentoService.autocompletar(1);
			return "redirect:/sesionTerapiaController/new";
	}
}
