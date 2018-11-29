
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	//R2  //R11.3
	@Query("select t.applications from Task t where t.customer.id=?1")
	Collection<Application> findAllByCustomerId(int customerId);

	@Query("select h.application from HandyWorker h where h.id=?1")
	Collection<Application> findAllApplicationByHandyWorkerId(int HandyWorkerId);

	@Query("select a from Application a where a.status=?1")
	Collection<Application> findAllApplicationByStatus(String Status);
	
	@Query("select h.application from HandyWorker h where h.id=?1")
	Collection<Application> findAllByHandyWorkerId(int HandyWorkerId);

}
