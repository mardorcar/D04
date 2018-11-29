
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Finder;
import domain.HandyWorker;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class handyWorkerServiceTest extends AbstractTest {

	@Autowired
	HandyWorkerService	handyWorkerService;

	@Autowired
	BoxService			boxService;

	@Autowired
	FinderService		finderService;


	@Test
	public void testSavedHandyWorker() {

		this.authenticate("handyWorker1");
		HandyWorker handyWorker;
		final HandyWorker saved;

		final Collection<HandyWorker> handyWorkers;
		handyWorker = this.handyWorkerService.create();

		handyWorker.setName("pepe");
		handyWorker.setMiddeName("dominguez");
		handyWorker.setSurname("antonio");
		handyWorker.setPhoto("https://www.google.es/");
		handyWorker.setEmail("pp@gmail.com");
		handyWorker.setPhoneNumber("+34954446611");
		handyWorker.setAddress("Biblioteca");
		handyWorker.setMake("pp618");
		final Finder finder = this.finderService.findFinderByHandyWorkerId(624);
		handyWorker.setFinder(finder);
		saved = this.handyWorkerService.save(handyWorker);
		handyWorkers = this.handyWorkerService.findAll();
		Assert.isTrue(handyWorkers.contains(saved));

	}
}
