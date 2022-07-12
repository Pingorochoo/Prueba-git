package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Plan;
import pe.edu.upc.repository.IPlanRepository;
import pe.edu.upc.service.IPlanService;

@Service
public class PlanServiceImpl implements IPlanService{
	
	@Autowired
	private IPlanRepository planRepository;

	@Override
	public void insert(Plan plan) {
		planRepository.save(plan);
	}

	@Override
	public List<Plan> list() {
		return planRepository.findAll();
	}

	@Override
	public void delete(int idPlan) {
		planRepository.deleteById(idPlan);
	}

	@Override
	public Optional<Plan> listId(int idPlan) {
		return planRepository.findById(idPlan);
	}

	@Override
	public void update(Plan plan) {
		planRepository.save(plan);
	}

	@Override
	public Page<Plan> getAll(Pageable pageable) {
		return planRepository.findAll(pageable);
	}

	@Override
	public List<Plan> listNoInsertados() {
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
	public List<Plan> getAllbyText(String palabraClave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Plan> getAllbyTextPage(Pageable pageable, String palabraClave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plan planNoInsertado() {
		// TODO Auto-generated method stub
		return null;
	}


}
