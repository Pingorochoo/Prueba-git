package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Psicologo;

public interface IPsicologoService {
	
	public void insert(Psicologo psicologo);
	public List<Psicologo>list();
	public List<Psicologo>listContratosVencer();
	public List<Psicologo>listConsultaContratosVencer(int diasConsulta);
	public void delete(int idPsicologo);
	Optional<Psicologo> listId(int idPsicologo);
	public void update(Psicologo psicologo);
}
