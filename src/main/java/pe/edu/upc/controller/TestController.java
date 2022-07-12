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
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.upc.entity.Test;
import pe.edu.upc.service.ITestService;

@Controller
@RequestMapping("testController")
public class TestController {

	@Autowired
	private ITestService testService;
	
	@GetMapping("/new")
	public String newTest(Model model) {
		model.addAttribute("test", new Test());
		return"test/frmRegister";
	}
	@PostMapping("/save")
	public String saveTest(@Valid Test test, BindingResult binRes, Model model){
		if(binRes.hasErrors()) {
			model.addAttribute("error", "ocurrio un error: ");
			return "test/frmRegister";
		}else {
			testService.insert(test);
			model.addAttribute("mensaje", "se guardo correctamente");
			//return "redirect:/testController/new";
			return "redirect:/sesionTerapiaController/new";
		}  
	}
	@RequestMapping("/ac")
	public String autoCompletar(){
			testService.autocompletar(5);
			return "redirect:/testController/toList";
	}
	
	@RequestMapping("/delete")
	public String deleteTest(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if(id!=null&&id>0) {
				testService.delete(id);
				model.put("listaTest", testService.list());//preguntar a la miss 
			} 
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return "redirect:/testController/toList";  
	}
	
	
	@GetMapping("/toList")
	public String toListTest(@RequestParam Map<String, Object> params,Model model) {
		try {
			int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
			PageRequest pageRequest = PageRequest.of(page, 5);
			Page<Test> pageTest = testService.getAll(pageRequest);
			int totalPage = pageTest.getTotalPages(); 
			if(totalPage > 0) {
				List<Integer> numeroDePaginas = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
				model.addAttribute("numeroDePaginas", numeroDePaginas);
			}
			model.addAttribute("listaTest", pageTest.getContent());
			model.addAttribute("current", page + 1);
			model.addAttribute("next", page + 2);
			model.addAttribute("prev", page);
			model.addAttribute("last", totalPage);
			model.addAttribute("test", new String());
			
		} catch (Exception e) {
			model.addAttribute("error", e.getCause());
		}
		return "test/frmList";
	}
	
	@RequestMapping("goUpdate/{id}")
	public String goUpdateTest(@PathVariable int id, Model model) {
		Optional<Test> test =testService.listId(id);
		model.addAttribute("testEditar", test.get());
		return "test/frmUpdate";
	}
	@RequestMapping("update")
	public String updateTest(Test test) {
		testService.update(test);
		return "redirect:/testController/toList";
	}
	
	@GetMapping("/search")
	public String searchFor(@RequestParam Map<String, Object> params,Model model, @Param("link") String link) {
		try {
//			int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
//			PageRequest pageRequest = PageRequest.of(page, 5);
//			Page<Test> pageTest = testService.getAllbyTextPage(pageRequest,palabraClave);
//			int totalPage = pageTest.getTotalPages(); 
//			if(totalPage > 0) {
//				List<Integer> numeroDePaginas = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
//				model.addAttribute("numeroDePaginas", numeroDePaginas);
//			}
//			//model.addAttribute("listaTest", pageTest.getContent());
//			model.addAttribute("current", page + 1);
//			model.addAttribute("next", page + 2);
//			model.addAttribute("prev", page);
//			model.addAttribute("last", totalPage);
			
			model.addAttribute("listaTest", testService.getAllbyText(link));
			
		} catch (Exception e) {
			model.addAttribute("error", e.getCause());
		}
		return "test/frmList";
	}
	@RequestMapping("/editLastInserted")
	public String editarUltimoInsertado(Model model) {
		try {
			model.addAttribute("psa", testService.testNoInsertado());
		} catch (Exception e) {
			model.addAttribute("error", e.getCause());
		}
		return "test/frmUpdateInSesionTerapia";
	}
	@RequestMapping("/updateLastInserted")
	public String actualizarUltimoInsertado(Test test) {
		testService.update(test);
		return "redirect:/sesionTerapiaController/new";
	}
}
