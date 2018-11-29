
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	//	@Query("select t from Task t")
	//	Collection<Task> findAllTask();

	@Query("select c.tasks from Customer c where c.userAccount.id=?1")
	Collection<Task> findTasksByIdCustomer(int CustomerId);

	@Query("select f.task from Finder f where f.id=?1")
	Collection<Task> findTasksByIdFinder(int finderId);

	@Query("select t from Task t where t.category=?1")
	Collection<Task> findTaskByIdCategory(int id);

}
