
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
import domain.Actor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ActorServiceTest extends AbstractTest {

	@Autowired
	private BoxService		boxService;

	@Autowired
	private ActorService	actorService;


	@Test
	public void testActorSave() {
		final Actor actor;
		final Actor saved;
		final Collection<Actor> actors;
		actor = this.actorService.create();

		actor.setName("Pepe");
		actor.setMiddeName("Raimundo");
		actor.setSurname("Amador");
		actor.setPhoto("https://www.google.es/");
		actor.setEmail("pp@gmail.com");
		actor.setPhoneNumber("+34954446611");
		actor.setAddress("Biblioteca");
		saved = this.actorService.save(actor);
		actors = this.actorService.findAll();
		Assert.isTrue(actors.contains(saved));
	}

}
