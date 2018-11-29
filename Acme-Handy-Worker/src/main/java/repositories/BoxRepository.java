
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Box;

@Repository
public interface BoxRepository extends JpaRepository<Box, Integer> {

	//R4
	@Query("select a.boxes from Actor a where a.id=?1")
	Collection<Box> findAllByActorId(int actorId);
	
	
	@Query("select a.boxes from Actor a where a.userAccount.id=?1")
	Collection<Box> findAllByUserAccountId(int userAccountId);

}
