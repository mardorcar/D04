
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

	@Query("select r.notes from Report r join r.complaint as co where co member of (select c.complaints from Customer c where c.id=?1)")
	Collection<Note> findByCustomerId(int customerId);

	@Query("select r.reports.notes from Referee r where r.id=?1")
	Collection<Note> findByRefereeId(int refereeId);

}
