package pe.edu.upc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Paciente;
import pe.edu.upc.entity.SesionTerapia;
import pe.edu.upc.entity.Test;
import pe.edu.upc.repository.IPacienteRepository;
import pe.edu.upc.repository.ISesionTerapiaRepository;
import pe.edu.upc.repository.ITestRepository;
import pe.edu.upc.repository.UserRepository;
import pe.edu.upc.service.ISesionTerapiaService;
import pe.edu.upc.service.ITestService;

@Service
public class TestServiceimpls implements ITestService {
	@Autowired
	private ITestRepository testRepository;
	@Autowired
	private ISesionTerapiaService sesionTerapiaService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IPacienteRepository pacienteRepository;
	@Autowired
	private ISesionTerapiaRepository sesionTerapiaRepository;
	@Override
	public void insert(Test test) {
		testRepository.save(test);
	}

	@Override
	public List<Test> list() {
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		UserDetails userDetails = null;
//		if (principal instanceof UserDetails) {
//		  userDetails = (UserDetails) principal;
//		}
//		String userName = userDetails.getUsername();
//		if(userName.equals("admin")) {
//		}
//		else 
//			return listPorPaciente();
		return testRepository.findAll();
	}

	@Override
	public void delete(int idTest) {
		Test test=testRepository.findById(idTest).get();
		if(test.getSesionTerapia()!=null) 
			sesionTerapiaService.delete(test.getSesionTerapia().getId());
		else
			testRepository.deleteById(idTest);
		
//		List<Integer> listaIdSesionesEncontradas = new ArrayList<Integer>();
//		for (SesionTerapia sesionTerapia : sesionTerapiaService.list()) {
//			listaIdSesionesEncontradas.add(sesionTerapia.getIdSesionTerapia());
//		}
//		if (listaIdSesionesEncontradas.size() > 0)
//			for (Integer idSesionEliminar : listaIdSesionesEncontradas) {
//				sesionTerapiaService.delete(idSesionEliminar);
//			}
//		else
//			testRepository.deleteById(idTest);
	}

	@Override
	public Optional<Test> listId(int idTest) {
		return testRepository.findById(idTest);
	}

	@Override
	public void update(Test test) {
		testRepository.save(test);
	}

	@Override
	public Page<Test> getAll(Pageable pageable) {
		
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		UserDetails userDetails = null;
//		if (principal instanceof UserDetails) {
//		  userDetails = (UserDetails) principal;
//		}
//		String userName = userDetails.getUsername();
//		if(userName.equals("admin")) {
//			return testRepository.findAll(pageable);
//		}
//		else 
//			return  listPorPaciente();
		
		
		
		
		
		
		return testRepository.findAll(pageable);
	}

	@Override
	public List<Test> listNoInsertados() {
		List<Test> listaNoInsertados = new ArrayList<Test>();
		for (Test test : testRepository.findAll()) {
			if (test.getSesionTerapia() == null)
				listaNoInsertados.add(test);
		}
		return listaNoInsertados;
	}

	@Override
	public void autocompletar(int cantidadInsertar) {
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, 1988);
//		cal.set(Calendar.MONTH, Calendar.JANUARY);
//		cal.set(Calendar.DAY_OF_MONTH, 1);
//		Date dateRepresentation = cal.getTime();
//		for (int i = 0; i < cantidadInsertar; i++) {
//			int numero = (int) (Math.random() * 100 + 1);
//			int numero2 = (int) (Math.random() * 100 + 1);
//			Test test = new Test((int) (Math.random() * 1000), numero, String.valueOf(numero2), dateRepresentation,
//					dateRepresentation);
//			testRepository.save(test);
//
//		}
	}

	@Override
	public void deleteUnrelated() {
		List<Integer> idTestNoInsertador = new ArrayList<Integer>();
		for (Test test : listNoInsertados()) {
			idTestNoInsertador.add(test.getId());
		}
		for (Integer idTest : idTestNoInsertador) {
			delete(idTest);
		}
	}

	@Override
	public List<Test> getAllbyText(String link) {
			return testRepository.findByText(link);
	}

	@Override
	public Page<Test> getAllbyTextPage(Pageable pageable, String palabraClave) {
		    if (getAllbyText(palabraClave) == null) {
		        throw new IllegalArgumentException("To create a Page, the list mustn't be null!");
		    }

		    int startOfPage = pageable.getPageNumber() * pageable.getPageSize();
		    if (startOfPage > getAllbyText(palabraClave).size()) {
		        return new PageImpl<>(new ArrayList<>(), pageable, 0);
		    }

		    int endOfPage = Math.min(startOfPage + pageable.getPageSize(), getAllbyText(palabraClave).size());
		    return new PageImpl<>(getAllbyText(palabraClave).subList(startOfPage, endOfPage), pageable, getAllbyText(palabraClave).size());

	}
	@Override
	public Test testNoInsertado() {
		return listNoInsertados().get(0);
	}

	@Override
	public List<Test> listPorPaciente() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		String userName = userDetails.getUsername();
		Long idUser=userRepository.findByUsername(userName).getId();
		Paciente paciente =pacienteRepository.findByIdUsuario(idUser).get();
		List<SesionTerapia> sesionPorPaciente=sesionTerapiaRepository.findByPaciente(paciente.getId());
		List<Test> testPorPaciente= new ArrayList<Test>();
		for(SesionTerapia sesion:sesionPorPaciente) {
			testPorPaciente.add(testRepository.findById(sesion.getTest().getId()).get());
		}
		return testPorPaciente;
	}
	@Override
	public Page<Test> listPagePorPaciente(Pageable pageable) {
		return null;
	}
}
