package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Paciente;

public interface IPacienteService {
	public void insert(Paciente paciente);
	public List<Paciente> list();
	public List<Paciente> listNoSuscritos();
	public void delete(int idPaciente);
	Optional<Paciente> listId(int idPaciente);
	public void update(Paciente paciente);
}
