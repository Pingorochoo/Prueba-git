package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.edu.upc.entity.Boleta;

public interface IBoletaService {
	public void insert(Boleta boleta);
	public List<Boleta> list();
	public void delete(int id);
	public Optional<Boleta> listId(int id);
	public void update(Boleta boleta);
	public Page<Boleta> getAll(Pageable pageable);
	public List<Boleta> listNoInsertados();
	public void autocompletar(int cantidadInsertar);
	public void deleteUnrelated();
	public List<Boleta> getAllbyText(String palabraClave);
	public Page<Boleta> getAllbyTextPage(Pageable pageable,String palabraClave);
	public Boleta testNoInsertado();
}
