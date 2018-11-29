
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {
//Display catalog of tutorials 
	
          
	
	
	//showing tutorials of a handy worker
	@Query("select t from Tutorial t where t.handyworker.id=?1 ")
	Collection<Tutorial> findByHandyWorkerid(int id);
	
	//Showing tutorials of a handy worker by principal
	@Query("select t from Tutorial t where t.handyworker.userAccount.id=?1 " )
     Collection<Tutorial> findByPrincipal(int id);

	
	
	 
}
