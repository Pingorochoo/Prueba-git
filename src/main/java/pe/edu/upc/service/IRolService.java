package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.edu.upc.entity.Role;

public interface IRolService {
	public void insert(Role role);
	public List<Role> list();
	public Optional<Role> listId(Long id);
	public void update(Role role);
	public void delete(Long id);
	public Page<Role> list(Pageable pageable);
}
