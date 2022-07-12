package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.entity.Role;

public interface IRolRepository extends JpaRepository<Role, Long>{

}
