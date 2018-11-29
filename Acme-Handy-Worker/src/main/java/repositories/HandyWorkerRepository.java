
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.HandyWorker;

@Repository
public interface HandyWorkerRepository extends JpaRepository<HandyWorker, Integer> {

	@Query("select h from HandyWorker h where h.id=?1")
	HandyWorker findHandyWorkerByIdFinder(int handyWorkerId);

	@Query("select h from HandyWorker h where h.userAccount.id=?1")
	HandyWorker findHandyWorkerByUAId(int id);
}
