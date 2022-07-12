package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Test;

@Repository
public interface ITestRepository extends JpaRepository<Test, Integer> {

	//@Query(value="select * from test t where t.link=:link", nativeQuery=true)
	//@Query(value="select * from test t where t.link like %:link%", nativeQuery=true)
	//@Query(value="select * from test t where concat(t.nota,t.link) like %:link%", nativeQuery=true)
	@Query("SELECT p FROM Test p WHERE CONCAT(p.nota,p.link) LIKE %?1%")
	List<Test> findByText(String link);
}
