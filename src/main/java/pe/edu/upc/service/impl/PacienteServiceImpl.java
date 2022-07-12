package pe.edu.upc.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Paciente;
import pe.edu.upc.entity.Role;
import pe.edu.upc.repository.IPacienteRepository;
import pe.edu.upc.repository.IRolRepository;
import pe.edu.upc.repository.UserRepository;
import pe.edu.upc.service.IPacienteService;

@Service
public class PacienteServiceImpl implements IPacienteService {
	
	@Autowired
	private IPacienteRepository pacienteRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IRolRepository rolRepository;
	@Override
	public void insert(Paciente paciente) {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		paciente.setFechadeRegistro(date);
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		String userName = userDetails.getUsername();
		Role rol=rolRepository.findById(Long.valueOf(5)).get();
		if(!userName.equals("admin")) {
			userRepository.findByUsername(userName).setRoles(Arrays.asList(rol));
			paciente.setUser(userRepository.findByUsername(userName));
		}
		else {
			userRepository.findById(paciente.getUser().getId()).get().setRoles(Arrays.asList(rol));
		}
		pacienteRepository.save(paciente);
	}

	@Override
	public List<Paciente> list() {

		return pacienteRepository.findAll();
	}

	@Override
	public void delete(int idPaciente) {
		pacienteRepository.deleteById(idPaciente);
	}

	@Override
	public Optional<Paciente> listId(int idPaciente) {
		// TODO Auto-generated method stub
		return pacienteRepository.findById(idPaciente);
	}

	@Override
	public void update(Paciente paciente) {
		Paciente paciente2=pacienteRepository.findById(paciente.getId()).get();
		paciente.setSuscripcion(paciente2.getSuscripcion());
		paciente.setUser(paciente2.getUser());
		pacienteRepository.save(paciente);
	}

	@Override
	public List<Paciente> listNoSuscritos() {
		List<Paciente> noSuscritos= new ArrayList<Paciente>();
		for(Paciente pasc:list()) {
			if(pasc.getSuscripcion()==null)
				noSuscritos.add(pasc);
		}
		return noSuscritos;
	}


}
