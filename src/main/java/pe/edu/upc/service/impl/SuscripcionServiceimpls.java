package pe.edu.upc.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import pe.edu.upc.entity.Role;
import pe.edu.upc.entity.Suscripcion;
import pe.edu.upc.entity.Users;
import pe.edu.upc.repository.IBoletaRepository;
import pe.edu.upc.repository.IPacienteRepository;
import pe.edu.upc.repository.IPlanRepository;
import pe.edu.upc.repository.IRolRepository;
import pe.edu.upc.repository.ISuscripcionRepository;
import pe.edu.upc.repository.UserRepository;
import pe.edu.upc.service.ISuscripcionService;

@Service
public class SuscripcionServiceimpls implements ISuscripcionService {
	@Autowired
	private ISuscripcionRepository suscripcionRepository;
	@Autowired
	private IPlanRepository planRepository;
	@Autowired
	private IBoletaRepository boletaRepository;
	@Autowired
	private IPacienteRepository pacienteRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IRolRepository rolRepository;

	@Override
	public void insert(Suscripcion suscripcion) {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		suscripcion.setFechaDeRegistro(date);

		Boleta boleta = suscripcion.getBoleta();
		double monto = getMontoTotal(suscripcion);
		boleta.setDetalles(suscripcion.getBoleta().getDetalles());
		boleta.setFechaDePago(date);
		boleta.setMonto(monto);
		boleta.setMetodoDePago("Online-Visa");
		boletaRepository.save(boleta);

		suscripcionRepository.save(suscripcion);

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		String userName = userDetails.getUsername();
		if (!userName.equals("admin")) {
			Long idUsuario = userRepository.findByUsername(userName).getId();
			Paciente paciente = pacienteRepository.findByIdUsuario(idUsuario).get();
			paciente.setSuscripcion(suscripcion);
			pacienteRepository.save(paciente);
		}
		else {
			Paciente paciente = pacienteRepository.findById(suscripcion.getPaciente().getId()).get();
			paciente.setSuscripcion(suscripcion);
			pacienteRepository.save(paciente);
		}

		Role rol = rolRepository.findById(Long.valueOf(3)).get();
		Users user = userRepository.findByUsername(userName);
		// user.setRoles(Arrays.asList(rol));
		user.añadirRol(rol);
		userRepository.save(user);
	}

	@Override
	public List<Suscripcion> list() {
		return suscripcionRepository.findAll();
	}

	@Override
	public void delete(int idSuscripcion) {
		suscripcionRepository.deleteById(idSuscripcion);
	}

	@Override
	public Optional<Suscripcion> listId(int idSuscripcion) {
		return suscripcionRepository.findById(idSuscripcion);
	}

	@Override
	public void update(Suscripcion suscripcion) {
		suscripcionRepository.save(suscripcion);
	}

	@Override
	public Page<Suscripcion> getAll(Pageable pageable) {
		return suscripcionRepository.findAll(pageable);
	}

	@Override
	public List<Suscripcion> listNoInsertados() {
		List<Suscripcion> listaNoInsertados = new ArrayList<Suscripcion>();
		for (Suscripcion suscripcion : suscripcionRepository.findAll()) {
			if (suscripcion.getPaciente() == null)
				listaNoInsertados.add(suscripcion);
		}
		return listaNoInsertados;
	}

	@Override
	public void autocompletar(int cantidadInsertar) {
	}

	@Override
	public void deleteUnrelated() {
		List<Integer> idSuscripcionNoInsertador = new ArrayList<Integer>();
		for (Suscripcion suscripcion : listNoInsertados()) {
			idSuscripcionNoInsertador.add(suscripcion.getId());
		}
		for (Integer idSuscripcion : idSuscripcionNoInsertador) {
			delete(idSuscripcion);
		}
	}

	@Override
	public List<Suscripcion> getAllbyText(String palabraClave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Suscripcion> getAllbyTextPage(Pageable pageable, String palabraClave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Suscripcion suscripcionNoInsertada() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getMontoTotal(Suscripcion suscripcion) {
		double meses = (double) (suscripcion.getMeses());
		double precio = planRepository.findById(suscripcion.getPlan().getId()).get().getPrecio();
		return meses * precio;
	}

}
