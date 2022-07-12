package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.edu.upc.entity.DocumentoDeTrabajo;

public interface IDocumentoService {
	public void insert(DocumentoDeTrabajo DocumentoDeTrabajo);
	public List<DocumentoDeTrabajo> list();
	public List<DocumentoDeTrabajo> listPorPaciente();
	public void delete(int idDocumentoDeTrabajo);
	public Optional<DocumentoDeTrabajo> listId(int idDocumentoDeTrabajo);
	public void update(DocumentoDeTrabajo DocumentoDeTrabajo);
	public Page<DocumentoDeTrabajo> getAll(Pageable pageable);
	public List<DocumentoDeTrabajo> listNoInsertados();
	public void autocompletar(int cantidadInsertar);
	public void deleteUnrelated();
	public DocumentoDeTrabajo documentoNoInsertado();
}
