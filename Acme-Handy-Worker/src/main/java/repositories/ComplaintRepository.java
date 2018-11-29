
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

	@Query("select t.complaints from HandyWorker h join h.finder.task t where h.id=?1")
	Collection<Complaint> findComplaintsByHandyWorkerId(int handyWorkerId);

	@Query("select r.complaint from Referee r where r.id=?1")
	Collection<Complaint> findComplaintsByRefereeId(int refereeId);

	@Query("select c.complaints from Customer c where c.id=?1")
	Collection<Complaint> findComplaintsByCustomerId(int customerId);

	@Query("select t.complaints from Task t where t.id=?1")
	Collection<Complaint> findComplaintsByTaskId(int taskId);

	@Query("select c.complaints from Customer c where c.userAccount.id=?1")
	Collection<Complaint> findCustomerComplaintsByUserAccountrId(int userAccountId);

	@Query("select c from Complaint c join c.reports r where r is empty")
	Collection<Complaint> findComplaintsUnasiggned();
}
