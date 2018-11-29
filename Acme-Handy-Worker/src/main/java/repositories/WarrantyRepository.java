
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Warranty;

@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Integer> {

	//Showing the catalogue of warranties
	//	@Query("select w from warranty w")
	//	Collection<Warranty> FindAllWarranties();

	//warranty required by the task;
	@Query("select t.warranty from Task t where t.warranty.id=?1")
	Collection<Warranty> FindAllWarrantiesById();

}
