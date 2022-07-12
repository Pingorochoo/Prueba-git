package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.edu.upc.entity.Plan;

public interface IPlanService {
	public void insert(Plan plan);
	public List<Plan> list();
	public void delete(int idPlan);
	public Optional<Plan> listId(int idPlan);
	public void update(Plan plan);
	public Page<Plan> getAll(Pageable pageable);
	public List<Plan> listNoInsertados();
	public void autocompletar(int cantidadInsertar);
	public void deleteUnrelated();
	public List<Plan> getAllbyText(String palabraClave);
	public Page<Plan> getAllbyTextPage(Pageable pageable,String palabraClave);
	public Plan planNoInsertado();
}
