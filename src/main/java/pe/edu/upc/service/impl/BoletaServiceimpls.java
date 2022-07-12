package pe.edu.upc.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Boleta;
import pe.edu.upc.entity.Paciente;
import pe.edu.upc.entity.Suscripcion;
import pe.edu.upc.entity.Users;
import pe.edu.upc.repository.IBoletaRepository;
import pe.edu.upc.repository.IPacienteRepository;
import pe.edu.upc.repository.ISuscripcionRepository;
import pe.edu.upc.repository.UserRepository;
import pe.edu.upc.service.IBoletaService;

@Service
public class BoletaServiceimpls implements IBoletaService {
	@Autowired
	private IBoletaRepository boletaRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IPacienteRepository pacienteRepository;
	@Autowired
	private ISuscripcionRepository suscripcionRepository;

	@Override
	public void insert(Boleta boleta) {
		boletaRepository.save(boleta);
	}

	@Override
	public List<Boleta> list() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		String userName = userDetails.getUsername();
		if (userName.equals("admin"))
			return boletaRepository.findAll();
		else {
			Users user=userRepository.findByUsername(userName);
			Paciente paciente=pacienteRepository.findByIdUsuario(user.getId()).get();
			Suscripcion suscripcion=suscripcionRepository.findById(paciente.getSuscripcion().getId()).get();
			Boleta boleta=boletaRepository.findById(suscripcion.getBoleta().getId()).get();
			return Arrays.asList(boleta);
		}
	}

	@Override
	public void delete(int id) {
		boletaRepository.deleteById(id);
	}

	@Override
	public Optional<Boleta> listId(int id) {
		return boletaRepository.findById(id);
	}

	@Override
	public void update(Boleta boleta) {
		boletaRepository.save(boleta);
	}

	@Override
	public Page<Boleta> getAll(Pageable pageable) {
		return boletaRepository.findAll(pageable);
	}

	@Override
	public List<Boleta> listNoInsertados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void autocompletar(int cantidadInsertar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUnrelated() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Boleta> getAllbyText(String palabraClave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Boleta> getAllbyTextPage(Pageable pageable, String palabraClave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boleta testNoInsertado() {
		// TODO Auto-generated method stub
		return null;
	}

}
