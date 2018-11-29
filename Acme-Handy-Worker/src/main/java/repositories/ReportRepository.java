
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

	@Query("select c.complaints.reports from Customer c where c.id=?1")
	Collection<Report> findByCustomerId(int customerId);

	@Query("select r.reports from Referee r where r.id=?1")
	Collection<Report> findByRefereeId(int refereeId);

}
