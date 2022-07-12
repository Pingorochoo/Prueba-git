package pe.edu.upc.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.upc.entity.Role;
import pe.edu.upc.service.IRolService;
@Controller
@RequestMapping("rolController")
public class RolController {
	@Autowired
	private IRolService IRolService;
	
	@GetMapping("/new")
	public String newRol(Model model) {
		model.addAttribute("rol", new Role());
		return"rol/frmRegister";
	}
	@RequestMapping("/delete")
	public String deleteRol(Map<String, Object> model, @RequestParam(value = "id") Long id) {
		try {
			if(id!=null&&id>0) {
				IRolService.delete(id);
				model.put("listaTest", IRolService.list());//preguntar a la miss 
			} 
		} catch (Exception e) {
			model.put("error", e.getMessage());
		}
		return "redirect:/rolController/toList";  
	}
	@GetMapping("/toList")
	public String toListRol(@RequestParam Map<String, Object> params,Model model) {
		try {
			int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
			PageRequest pageRequest = PageRequest.of(page, 5);
			Page<Role> pageTest = IRolService.list(pageRequest);
			int totalPage = pageTest.getTotalPages(); 
			if(totalPage > 0) {
				List<Integer> numeroDePaginas = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
				model.addAttribute("numeroDePaginas", numeroDePaginas);
			}
			model.addAttribute("listaRole", pageTest.getContent());
			model.addAttribute("current", page + 1);
			model.addAttribute("next", page + 2);
			model.addAttribute("prev", page);
			model.addAttribute("last", totalPage);
			model.addAttribute("test", new String());
			
		} catch (Exception e) {
			model.addAttribute("error", e.getCause());
		}
		return "rol/frmList";
	}
	@RequestMapping("goUpdate/{id}")
	public String goUpdateRole(@PathVariable Long id, Model model) {
		Optional<Role> test =IRolService.listId(id);
		model.addAttribute("rolEditar", test.get());
		return "rol/frmUpdate";
	}
	@RequestMapping("update")
	public String updateRole(Role rol) {
		IRolService.update(rol);
		return "redirect:/rolController/toList";
	}
}
