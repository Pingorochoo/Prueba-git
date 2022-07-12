package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Role;
import pe.edu.upc.repository.IRolRepository;
import pe.edu.upc.service.IRolService;
@Service
public class RolServiceImpls implements IRolService {
	@Autowired
	private IRolRepository rolRepository;
	@Override
	public void insert(Role role) {
		rolRepository.save(role);
	}

	@Override
	public List<Role> list() {
		return rolRepository.findAll();
	}

	@Override
	public Optional<Role> listId(Long id) {
		return rolRepository.findById(id);
	}

	@Override
	public void update(Role role) {
		rolRepository.save(role);
	}

	@Override
	public void delete(Long id) {
		rolRepository.deleteById(id);
	}

	@Override
	public Page<Role> list(Pageable pageable) {
		return rolRepository.findAll(pageable);
	}


}
