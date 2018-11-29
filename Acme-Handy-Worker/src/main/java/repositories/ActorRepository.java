
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	//R12.4
	//	@Query("select a from Actor a ")
	//	Collection<Actor> findAllByActors();

	@Query("select a from Actor a where a.id=?1")
	Actor findActorById(int userAccountId);

	@Query("select a from Actor a join a.boxes b where b.id=?1")
	Collection<Actor> findActorByBoxId(int boxId);
}
