package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.SesionTerapia;

@Repository
public interface ISesionTerapiaRepository extends JpaRepository<SesionTerapia, Integer>{
	
	 @Query(value="select * from sesion_terapia where id_paciente=:idPaciente", nativeQuery=true)
	public List<SesionTerapia> findByPaciente(int idPaciente);
}
