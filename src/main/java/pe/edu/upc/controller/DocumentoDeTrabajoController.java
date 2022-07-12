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

import pe.edu.upc.entity.DocumentoDeTrabajo;
import pe.edu.upc.service.IDocumentoService;

@Controller
@RequestMapping("documentoDeTrabajoController")
public class DocumentoDeTrabajoController {
	@Autowired
	private IDocumentoService documentoService;
	
	@GetMapping("/new")
	public String newDocumentoDeTrabajo(Model model) {
		model.addAttribute("documento", new DocumentoDeTrabajo());
		return "documentoDeTrabajo/frmRegister";
	}

	@PostMapping("/save")
	public String saveDocumentoDeTrabajo(@Valid DocumentoDeTrabajo documentoDeTrabajo, BindingResult binRes, Model model) {
		if (binRes.hasErrors()) {
			model.addAttribute("error", "ocurrio un error: ");
			return "documentoDeTrabajo/frmRegister";
		} else {
			documentoService.insert(documentoDeTrabajo);
			model.addAttribute("mensaje", "se guardo correctamente");
			//return "redirect:/documentoDeTrabajoController/new";
			return "redirect:/sesionTerapiaController/new";
		}
	}
	
	@RequestMapping("/ac")
	public String autoCompletar(){
			documentoService.autocompletar(15);
		return "redirect:/documentoDeTrabajoController/toList";
	}
	
	@RequestMapping("/delete")
	public String deleteDocumentoDeTrabajo(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if(id!=null&&id>0) {
				documentoService.delete(id);
				model.put("listaDocumentos", documentoService.list());//preguntar a la miss 
			} 
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return "redirect:/documentoDeTrabajoController/toList";  
	}
	@GetMapping("/toList")
	public String toListDocumentoDeTrabajo(@RequestParam Map<String, Object> params,Model model) {
		try {
			int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
			PageRequest pageRequest = PageRequest.of(page, 5);
			Page<DocumentoDeTrabajo> pageTest = documentoService.getAll(pageRequest);
			int totalPage = pageTest.getTotalPages(); 
			if(totalPage > 0) {
				List<Integer> numeroDePaginas = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
				model.addAttribute("numeroDePaginas", numeroDePaginas);
			}
			model.addAttribute("current", page + 1);
			model.addAttribute("next", page + 2);
			model.addAttribute("prev", page);
			model.addAttribute("last", totalPage);
			model.addAttribute("listaDocumentos", pageTest.getContent());
		} catch (Exception e) {
			model.addAttribute("error", e.getCause());
		}
		return "documentoDeTrabajo/frmList";
	}
	
	@RequestMapping("goUpdate/{id}")
	public String goUpdateDocumentoDeTrabajo(@PathVariable int id, Model model) {
		Optional<DocumentoDeTrabajo> documento =documentoService.listId(id);
		model.addAttribute("documentoActualizar", documento.get());
		return "documentoDeTrabajo/frmUpdate";
	}
	@RequestMapping("update")
	public String updateDocumentoDeTrabajo(DocumentoDeTrabajo documentoDeTrabajo) {
		documentoService.update(documentoDeTrabajo);
		return "redirect:/documentoDeTrabajoController/toList";
	}

	@RequestMapping("/editLastInserted")
	public String editarUltimoInsertado(Model model) {
		try {
			model.addAttribute("documentoActualizar", documentoService.documentoNoInsertado());
		} catch (Exception e) {
			model.addAttribute("error", e.getCause());
		}
		return "documentoDeTrabajo/frmUpdateInSesionTerapia";
	}
	@RequestMapping("/updateLastInserted")
	public String actualizarUltimoInsertado(DocumentoDeTrabajo documentoDeTrabajo) {
		documentoService.update(documentoDeTrabajo);
		return "redirect:/sesionTerapiaController/new";
	}
}
