package pe.edu.upc.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.DocumentoDeTrabajo;
import pe.edu.upc.entity.Paciente;
import pe.edu.upc.entity.SesionTerapia;
import pe.edu.upc.entity.Test;
import pe.edu.upc.repository.IPacienteRepository;
import pe.edu.upc.repository.ISesionTerapiaRepository;
import pe.edu.upc.repository.UserRepository;
import pe.edu.upc.service.ICalificacionService;
import pe.edu.upc.service.IDocumentoService;
import pe.edu.upc.service.ISesionTerapiaService;
import pe.edu.upc.service.ITestService;
@Service
public class SesionTerapiaServiceimpls implements ISesionTerapiaService{
	@Autowired
	private ISesionTerapiaRepository sesionTerapiaRepository;

	@Autowired
	private IDocumentoService documentoService;
	@Autowired
	private ICalificacionService calificacionSerivce;
	@Autowired
	private ITestService testService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IPacienteRepository pacienteRepository;
	@Override
	public void insert(SesionTerapia sesionTerapia) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		sesionTerapia.setFechaDeLaSesion(date);
		sesionTerapiaRepository.save(sesionTerapia);
		testService.deleteUnrelated();
		calificacionSerivce.deleteUnrelated();
		documentoService.deleteUnrelated();
	}

	@Override
	public List<SesionTerapia> list() {
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		UserDetails userDetails = null;
//		if (principal instanceof UserDetails) {
//		  userDetails = (UserDetails) principal;
//		}
//		String userName = userDetails.getUsername();
//		if(userName.equals("admin")) {
//			return sesionTerapiaRepository.findAll();			
//		}
//		else {
//			return listPorPaciente();
//		}
		return sesionTerapiaRepository.findAll();
	}

	@Override
	public void delete(int idSesion) {
		// TODO Auto-generated method stub
		sesionTerapiaRepository.deleteById(idSesion);
		testService.deleteUnrelated();
		documentoService.deleteUnrelated();
		
	}

	@Override
	public Optional<SesionTerapia> listId(int idSesion) {
		// TODO Auto-generated method stub
		return sesionTerapiaRepository.findById(idSesion);
	}

	@Override
	public void update(SesionTerapia sesionTerapia) {
		SesionTerapia sesionTerapia1=sesionTerapiaRepository.findById(sesionTerapia.getId()).get();
		Optional<DocumentoDeTrabajo> documentoDeTrabajo=documentoService.
				listId(sesionTerapia1.getDocumento().getId());
		sesionTerapia.setDocumento(documentoDeTrabajo.get());
		Optional<Test> test=testService.
				listId(sesionTerapia1.getTest().getId());
		sesionTerapia.setTest(test.get());
		sesionTerapiaRepository.save(sesionTerapia);
	}

	@Override
	public List<SesionTerapia> sesionesPorPaciente(int idPaciente) {
		sesionTerapiaRepository.findByPaciente(idPaciente);
		return null;
	}

	@Override
	public List<SesionTerapia> listPorPaciente() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		String userName = userDetails.getUsername();
		Long idUser=userRepository.findByUsername(userName).getId();
		Paciente paciente =pacienteRepository.findByIdUsuario(idUser).get();
		List<SesionTerapia> porPaciente=sesionTerapiaRepository.findByPaciente(paciente.getId());
		return porPaciente;
	}

	@Override
	public void insertCalificacion(SesionTerapia sesionTerapia) {
		calificacionSerivce.insert(sesionTerapia.getCalificacion());
		SesionTerapia sesion=sesionTerapiaRepository.findById(sesionTerapia.getId()).get();
		sesion.setCalificacion(sesionTerapia.getCalificacion());
		sesionTerapiaRepository.save(sesion);
	}

	@Override
	public List<SesionTerapia> listNoCalidicadasPorPaciente() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		String userName = userDetails.getUsername();
		Long idUser=userRepository.findByUsername(userName).getId();
		Paciente paciente =pacienteRepository.findByIdUsuario(idUser).get();
		List<SesionTerapia> porPaciente=sesionTerapiaRepository.findByPaciente(paciente.getId());
		List<SesionTerapia> porPacienteNocalificadas= new ArrayList<SesionTerapia>();
		for(SesionTerapia sesion: porPaciente) {
			if(sesion.getCalificacion()==null)
				porPacienteNocalificadas.add(sesion);
		}
		return porPacienteNocalificadas;
	}
}
