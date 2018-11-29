
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
import domain.Box;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class BoxServiceTest extends AbstractTest {

	@Autowired
	private BoxService		boxService;

	@Autowired
	private ActorService	actorService;


	@Test
	public void testSaveBoxes() {
		this.authenticate("admin2");
		final Box box;
		final Box saved;
		final Collection<Box> boxes;
		box = this.boxService.create();
		box.setName("Trabajo");
		saved = this.boxService.save(box);
		boxes = this.boxService.findAll();
		Assert.isTrue(boxes.contains(saved));
		//		Assert.isTrue(boxes.isEmpty());

	}
}
