
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Support;

@Repository
public interface SupportRepository extends JpaRepository<Support, Integer> {

}
