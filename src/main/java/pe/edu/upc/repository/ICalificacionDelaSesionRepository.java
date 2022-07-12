package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.CalificacionDeLaSesion;

@Repository
public interface ICalificacionDelaSesionRepository extends JpaRepository<CalificacionDeLaSesion, Integer>{

}
