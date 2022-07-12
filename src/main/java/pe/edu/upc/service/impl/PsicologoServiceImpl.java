package pe.edu.upc.service.impl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Psicologo;
import pe.edu.upc.entity.Role;
import pe.edu.upc.repository.IPsicologoRepository;
import pe.edu.upc.repository.IRolRepository;
import pe.edu.upc.repository.UserRepository;
import pe.edu.upc.service.IPsicologoService;

@Service
public class PsicologoServiceImpl implements IPsicologoService {
	@Autowired
	private IPsicologoRepository psicologoRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IRolRepository rolRepository;
	@Override
	public void insert(Psicologo psicologo) {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		psicologo.setFechadeRegistro(date);
		Role rol=rolRepository.findById(Long.valueOf(2)).get();
		userRepository.findById(psicologo.getUser().getId()).get().setRoles(Arrays.asList(rol));
		psicologoRepository.save(psicologo);
	}

	@Override
	public List<Psicologo> list() {

		return psicologoRepository.findAll();
	}

	@Override
	public void delete(int idPsicologo) {
		psicologoRepository.deleteById(idPsicologo);
	}

	@Override
	public Optional<Psicologo> listId(int idPsicologo) {
		// TODO Auto-generated method stub
		return psicologoRepository.findById(idPsicologo);
	}

	@Override
	public void update(Psicologo psicologo) {
		Psicologo psicologo2=psicologoRepository.findById(psicologo.getId()).get();
		psicologo.setUser(psicologo2.getUser());
		psicologoRepository.save(psicologo);
	}

	@Override
	public List<Psicologo> listContratosVencer() {
		return psicologoRepository.contratosAVencer();
	}

	@Override
	public List<Psicologo> listConsultaContratosVencer(int diasConsulta) {
		// TODO Auto-generated method stub
		return psicologoRepository.consultaContratosAVencer(diasConsulta);
	}


}
