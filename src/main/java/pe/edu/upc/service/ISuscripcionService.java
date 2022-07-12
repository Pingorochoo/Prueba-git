package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.edu.upc.entity.Suscripcion;

public interface ISuscripcionService {
	public void insert(Suscripcion suscripcion);
	public List<Suscripcion> list();
	public void delete(int idSuscripcion);
	public Optional<Suscripcion> listId(int idSuscripcion);
	public void update(Suscripcion suscripcion);
	public Page<Suscripcion> getAll(Pageable pageable);
	public List<Suscripcion> listNoInsertados();
	public void autocompletar(int cantidadInsertar);
	public void deleteUnrelated();
	public List<Suscripcion> getAllbyText(String palabraClave);
	public Page<Suscripcion> getAllbyTextPage(Pageable pageable,String palabraClave);
	public Suscripcion suscripcionNoInsertada();
	public double getMontoTotal(Suscripcion suscripcion);
}
