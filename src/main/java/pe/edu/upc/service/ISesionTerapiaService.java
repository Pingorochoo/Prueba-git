package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.SesionTerapia;

public interface ISesionTerapiaService {
	public void insert(SesionTerapia sesionTerapia);
	public List<SesionTerapia> list();
	public List<SesionTerapia> listPorPaciente();
	public List<SesionTerapia> listNoCalidicadasPorPaciente();
	public void delete(int idSesion);
	public Optional<SesionTerapia> listId(int idSesion);
	public void update(SesionTerapia sesionTerapia);
	public List<SesionTerapia> sesionesPorPaciente(int idPaciente);
	public void insertCalificacion(SesionTerapia sesionTerapia);
}
