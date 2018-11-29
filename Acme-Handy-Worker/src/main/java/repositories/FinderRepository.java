
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Finder;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer> {

	//R37.1
	@Query("select h.finder from HandyWorker h where h.id=?1")
	Finder findByHandyWorkerId(int handyWorkerId);

	@Query("select f from Finder f join f.task t where t.id=?1")
	Finder findFinderByTaskId(int TaskId);
}
