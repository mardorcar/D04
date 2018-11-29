
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.EndorseRecord;

@Repository
public interface EndorseRecordRepository extends JpaRepository<EndorseRecord, Integer> {

}
