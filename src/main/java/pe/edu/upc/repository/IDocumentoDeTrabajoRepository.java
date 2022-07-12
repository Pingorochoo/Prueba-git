package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.DocumentoDeTrabajo;

@Repository
public interface IDocumentoDeTrabajoRepository extends JpaRepository<DocumentoDeTrabajo, Integer>{

}
