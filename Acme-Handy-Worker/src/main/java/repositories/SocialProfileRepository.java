
package repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SocialProfile;


@Repository
public interface SocialProfileRepository extends JpaRepository<SocialProfile, Integer> {
	
	//Showing profil of customer
	@Query("select p from SocialProfile p where p.actor.id=?1 ")
	SocialProfile findByActor(int id);

}
