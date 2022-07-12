package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Suscripcion;
@Repository
public interface ISuscripcionRepository extends JpaRepository<Suscripcion, Integer>{
	
}