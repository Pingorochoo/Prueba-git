package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Psicologo;
@Repository
public interface IPsicologoRepository extends JpaRepository<Psicologo, Integer>{
	
	@Query(value="select * from psicologo where fecha_final_contrato between current_date and current_date+30",
			nativeQuery=true)
	public List<Psicologo> contratosAVencer();
	@Query(value="select * from psicologo where fecha_final_contrato between current_date and current_date+:diasConsulta",
			nativeQuery=true)
	public List<Psicologo> consultaContratosAVencer(int diasConsulta);
}
