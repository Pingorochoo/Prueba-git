package pe.edu.upc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Paciente;
@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Integer>{
	@Query(value="select * from paciente p where p.id_User=:idUsuario", nativeQuery=true)
	public Optional<Paciente> findByIdUsuario(Long idUsuario);
}
