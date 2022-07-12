package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.edu.upc.entity.CalificacionDeLaSesion;

public interface ICalificacionService {
	public void insert(CalificacionDeLaSesion calificacionDeLaSesion);
	public List<CalificacionDeLaSesion> list();
	public void delete(int idCalificacion);
	public Optional<CalificacionDeLaSesion> listId(int idCalificacion);
	public void update(CalificacionDeLaSesion calificacionDeLaSesion);
	public Page<CalificacionDeLaSesion> getAll(Pageable pageable);
	public List<CalificacionDeLaSesion> listNoInsertados();
	public void autocompletar(int cantidadInsertar);
	public void deleteUnrelated();
}
