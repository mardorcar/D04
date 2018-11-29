
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Endorse;

@Repository
public interface EndorseRepository extends JpaRepository<Endorse, Integer> {

	//showing endorsements of Customer user
	@Query("select e from Endorse e where e.customerReceiver.userAccount.id=?1 ")
	Collection<Endorse> findByCustomerUserAccountid(int id);

	//showing endorsements of Handyworker sender user

	@Query("select e from Endorse e where e.handyWorkerSender.userAccount.id=?1 ")
	Collection<Endorse> findByUserHWSAccountid(int id);
	//showing endorsements of Handyworker reciever user
	@Query("select e from Endorse e where e.handyWorkerReceiver.userAccount.id=?1 ")
	Collection<Endorse> findByUserHWRAccountid(int id);
}
