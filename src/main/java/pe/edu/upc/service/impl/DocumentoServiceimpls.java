package pe.edu.upc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.DocumentoDeTrabajo;
import pe.edu.upc.entity.Paciente;
import pe.edu.upc.entity.SesionTerapia;
import pe.edu.upc.repository.IDocumentoDeTrabajoRepository;
import pe.edu.upc.repository.IPacienteRepository;
import pe.edu.upc.repository.ISesionTerapiaRepository;
import pe.edu.upc.repository.UserRepository;
import pe.edu.upc.service.IDocumentoService;

@Service
public class DocumentoServiceimpls implements IDocumentoService {
	@Autowired
	private IDocumentoDeTrabajoRepository documentoDeTrabajoRepository;
	@Autowired
	private SesionTerapiaServiceimpls sesionTerapiaServiceimpls;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IPacienteRepository pacienteRepository;
	@Autowired
	private ISesionTerapiaRepository sesionTerapiaRepository;

	@Override
	public void insert(DocumentoDeTrabajo documentoDeTrabajo) {
		// TODO Auto-generated method stub
		documentoDeTrabajoRepository.save(documentoDeTrabajo);
	}

	@Override
	public List<DocumentoDeTrabajo> list() {

//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		UserDetails userDetails = null;
//		if (principal instanceof UserDetails) {
//			userDetails = (UserDetails) principal;
//		}
//		String userName = userDetails.getUsername();
//		if (userName.equals("admin")) {
//		}
//		else return listPorPaciente();
		return documentoDeTrabajoRepository.findAll();
	}

	@Override
	public void delete(int idDocumentoDeTrabajo) {
		DocumentoDeTrabajo documentoDeTrabajo = documentoDeTrabajoRepository.findById(idDocumentoDeTrabajo).get();
		if (documentoDeTrabajo.getSesionTerapia() != null) {
			sesionTerapiaServiceimpls.delete(documentoDeTrabajo.getSesionTerapia().getId());// sesionTerapiaRepository.deleteById(idTest);
																							// esta funcion busca
																							// cualquier id pk o fp para
																							// eliminar
		} else
			documentoDeTrabajoRepository.deleteById(idDocumentoDeTrabajo);
	}

	@Override
	public Optional<DocumentoDeTrabajo> listId(int idDocumentoDeTrabajo) {
		// TODO Auto-generated method stub
		return documentoDeTrabajoRepository.findById(idDocumentoDeTrabajo);
	}

	@Override
	public void update(DocumentoDeTrabajo documentoDeTrabajo) {
		documentoDeTrabajoRepository.save(documentoDeTrabajo);
	}

	@Override
	public Page<DocumentoDeTrabajo> getAll(Pageable pageable) {
		return documentoDeTrabajoRepository.findAll(pageable);
	}

	@Override
	public List<DocumentoDeTrabajo> listNoInsertados() {
		List<DocumentoDeTrabajo> listaNoInsertados = new ArrayList<DocumentoDeTrabajo>();
		for (DocumentoDeTrabajo documentoDeTrabajo : documentoDeTrabajoRepository.findAll()) {
			if (documentoDeTrabajo.getSesionTerapia() == null)
				listaNoInsertados.add(documentoDeTrabajo);
		}
		return listaNoInsertados;
	}

	@Override
	public void autocompletar(int cantidadInsertar) {
//		for(int i=0;i<cantidadInsertar;i++) {
//			int numero = (int)(Math.random()*100+1);
//			int  titulo=(int)(Math.random()*1000+1);
//			int  link=(int)(Math.random()*10000+1);
//			DocumentoDeTrabajo documentoDeTrabajo=new DocumentoDeTrabajo((int)(Math.random()*1000),String.valueOf(numero),String.valueOf(titulo),String.valueOf(link));
//			documentoDeTrabajoRepository.save(documentoDeTrabajo);
//			
//		}
	}

	@Override
	public void deleteUnrelated() {
		List<Integer> idDocumentosNoInsertados = new ArrayList<Integer>();
		for (DocumentoDeTrabajo documentoDeTrabajo : listNoInsertados()) {
			idDocumentosNoInsertados.add(documentoDeTrabajo.getId());
		}
		for (Integer idDocumento : idDocumentosNoInsertados) {
			delete(idDocumento);
		}
	}

	@Override
	public DocumentoDeTrabajo documentoNoInsertado() {
		return listNoInsertados().get(0);
	}

	@Override
	public List<DocumentoDeTrabajo> listPorPaciente() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		String userName = userDetails.getUsername();
		Long idUser = userRepository.findByUsername(userName).getId();
		Paciente paciente = pacienteRepository.findByIdUsuario(idUser).get();
		List<SesionTerapia> sesionPorPaciente = sesionTerapiaRepository.findByPaciente(paciente.getId());
		List<DocumentoDeTrabajo> documentoPorPaciente = new ArrayList<DocumentoDeTrabajo>();
		for (SesionTerapia sesion : sesionPorPaciente) {
			documentoPorPaciente.add(documentoDeTrabajoRepository.findById(sesion.getTest().getId()).get());
		}
		return documentoPorPaciente;
	}

}
