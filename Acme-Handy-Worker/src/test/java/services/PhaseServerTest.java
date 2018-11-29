
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
import domain.Phase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class PhaseServerTest extends AbstractTest {

	@Autowired
	PhaseService	phaseService;


	@Test
	public void testPhaseSave() {

		this.authenticate("handyWorker1");
		final Phase phase;
		final Phase saved;
		final Collection<Phase> phases;
		phase = this.phaseService.create();

		phase.setDescription("shola soy una descripcion");
		phase.setEndMoment(new Date(System.currentTimeMillis() + 5000));
		phase.setStartMoment(new Date(System.currentTimeMillis()));
		phase.setTitle("titulo");
		saved = this.phaseService.save(phase);
		phases = this.phaseService.findAll();
		Assert.isTrue(phases.contains(saved));
	}

}
