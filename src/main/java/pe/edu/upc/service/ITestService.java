package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.edu.upc.entity.Test;

public interface ITestService {
	public void insert(Test test);
	public List<Test> list();
	public List<Test> listPorPaciente();
	public void delete(int idTest);
	public Optional<Test> listId(int idTest);
	public void update(Test test);
	public Page<Test> getAll(Pageable pageable);
	public Page<Test> listPagePorPaciente(Pageable pageable);
	public List<Test> listNoInsertados();
	public void autocompletar(int cantidadInsertar);
	public void deleteUnrelated();
	
	public List<Test> getAllbyText(String link);
	
	public Page<Test> getAllbyTextPage(Pageable pageable,String palabraClave);
	public Test testNoInsertado();
}
