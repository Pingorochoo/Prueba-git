package pe.edu.upc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.CalificacionDeLaSesion;
import pe.edu.upc.repository.ICalificacionDelaSesionRepository;
import pe.edu.upc.service.ICalificacionService;


@Service
public class CalificacionServiceImpl implements ICalificacionService{
	@Autowired
	private ICalificacionDelaSesionRepository calificacionDelaSesionRepository;
	@Autowired
	private SesionTerapiaServiceimpls sesionTerapiaServiceimpls;
	
	
	@Override
	public void insert(CalificacionDeLaSesion calificacionDeLaSesion) {
		calificacionDelaSesionRepository.save(calificacionDeLaSesion);
	}

	@Override
	public List<CalificacionDeLaSesion> list() {
		return calificacionDelaSesionRepository.findAll();
	}

	@Override
	public void delete(int idCalificacionDeLaSesion) {
		CalificacionDeLaSesion calificacionDeLaSesion=calificacionDelaSesionRepository.findById(idCalificacionDeLaSesion).get();
		if(calificacionDeLaSesion.getSesionTerapia()!=null) {
			sesionTerapiaServiceimpls.delete(idCalificacionDeLaSesion);//sesionTerapiaRepository.deleteById(idTest); esta funcion busca cualquier id pk o fp para eliminar
		}
		else
			calificacionDelaSesionRepository.deleteById(idCalificacionDeLaSesion);
	}

	@Override
	public Optional<CalificacionDeLaSesion> listId(int idCalificacion) {
		return calificacionDelaSesionRepository.findById(idCalificacion);
	}

	@Override
	public void update(CalificacionDeLaSesion calificacionDeLaSesion) {
		calificacionDelaSesionRepository.save(calificacionDeLaSesion);
	}



	@Override
	public List<CalificacionDeLaSesion> listNoInsertados() {
		List<CalificacionDeLaSesion> listaNoInsertados=new ArrayList<CalificacionDeLaSesion>();
		for(CalificacionDeLaSesion calificacionDeLaSesion: calificacionDelaSesionRepository.findAll()) {
			if(calificacionDeLaSesion.getSesionTerapia()==null)
				listaNoInsertados.add(calificacionDeLaSesion);
		}
		return listaNoInsertados;
	}

	@Override
	public Page<CalificacionDeLaSesion> getAll(Pageable pageable) {
		return calificacionDelaSesionRepository.findAll(pageable);
	}

	
	@Override
	public void autocompletar(int cantidadInsertar) {
		
	}

	@Override
	public void deleteUnrelated() {
		List<Integer> idCalificacionesNoInsertadas= new ArrayList<Integer>();
		for(CalificacionDeLaSesion calificacion: listNoInsertados()) {
			idCalificacionesNoInsertadas.add(calificacion.getId());
		}
		for(Integer idCalificaciones: idCalificacionesNoInsertadas) {
			delete(idCalificaciones);
		}
	}

}
