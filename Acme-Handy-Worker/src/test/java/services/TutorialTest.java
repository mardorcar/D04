
package services;

import java.sql.Date;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Tutorial;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class TutorialTest extends AbstractTest {

	@Autowired
	private TutorialService		tutorialService;
	@Autowired
	private HandyWorkerService	handyWorkerService;
	@Autowired
	private SectionService		sectionService;


	@Test
	public void testSaveTutorial() {

		this.authenticate("handyWorker");
		final Tutorial tutorial = new Tutorial();
		tutorial.setTitle("tutorial 1");
		tutorial.setPictures("https://www.pictures.es/25");
		tutorial.setSummary("summary1");
		tutorial.setHandyworker(this.handyWorkerService.findById(243));
		tutorial.setSection(this.sectionService.findAll());
		tutorial.setMoment(new Date(21 / 21 / 2017));
		this.tutorialService.SaveTutorial(tutorial);
		this.authenticate(null);

	}

	@Test
	public void findAll() {
		Collection<Tutorial> all;

		all = this.tutorialService.findAll();
		Assert.isTrue(!all.isEmpty());

	}

	@Test
	public void testfindOne() {
		Tutorial tutorial;

		tutorial = this.tutorialService.findOne(360448);

		Assert.notNull(tutorial);

	}

	@Test
	public void testfindByHandyWorkerid() {

		Collection<Tutorial> all;

		all = this.tutorialService.findByHandyWorkerid(243);

		Assert.isTrue(!all.isEmpty());

	}

	@Test
	public void testfindByPrincipal() {

		this.authenticate("handyWorker");

		Collection<Tutorial> all;

		all = this.tutorialService.findByPrincipal();

		Assert.isTrue(!all.isEmpty());

		this.authenticate(null);
	}

	@Test
	public void testDelete() {
		this.authenticate("handyworker1");

		final Tutorial tutorial = this.tutorialService.findOne(229376);

		this.tutorialService.delete(tutorial);
		this.authenticate(null);
	}

}
