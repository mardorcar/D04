
package services;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Finder;
import domain.Task;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FinderServiceTest extends AbstractTest {

	@Autowired
	FinderService		finderService;

	//	@Autowired
	//	private HandyWorkerService	handyWorkerService;

	@Autowired
	private TaskService	taskService;


	@Test
	public void testSavedFinder() {

		this.authenticate("handyWorker1");
		final Finder finder;
		final Finder saved;

		final Collection<Finder> finders;
		finder = this.finderService.create();
		finder.setCategory("informatica");
		finder.setStartDate(new Date(System.currentTimeMillis() + 8000));
		finder.setEndDate(new Date(System.currentTimeMillis() + 5000));
		finder.setKeyWord("manuel");
		finder.setMaxPrice(45.0);
		finder.setMinPrice(10.1);
		final Collection<Task> tasks = this.taskService.findAll();
		finder.setTask(tasks);
		finder.setWarranty("garatia");
		saved = this.finderService.save(finder);
		finders = this.finderService.findAll();
		Assert.isTrue(finders.contains(saved));

	}
}
