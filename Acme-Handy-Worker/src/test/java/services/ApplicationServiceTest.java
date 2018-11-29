
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Application;
import domain.CreditCard;
import domain.Task;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ApplicationServiceTest extends AbstractTest {

	@Autowired
	private ApplicationService	applicationService;
	@Autowired
	private TaskService			taskService;


	@Test
	public void testApplicationSave() {
		this.authenticate("handyWorker1");
		final Application application;
		final Application saved;
		final Collection<Application> applications;
		application = this.applicationService.create();
		final Collection<String> comments = new ArrayList<String>();
		final List<Task> tasks = new ArrayList<>(this.taskService.findAll());
		final Task task = tasks.get(0);

		final CreditCard creditCard = new CreditCard();
		creditCard.setHolderName("Pepe");
		creditCard.setBrandName("Lopez");
		creditCard.setNumber("4485715860868875");
		creditCard.setExpirationMonth("Enero");
		creditCard.setExpirationYear("1998");

		application.setCreditCard(creditCard);
		application.setComments(comments);
		//		application.setStatus("PENDING");
		application.setTask(task);
		application.setDatetime(new Date(System.currentTimeMillis()));
		application.setMaximumPrice(0.0);
		saved = this.applicationService.save(application);
		applications = this.applicationService.findAll();
		Assert.isTrue(applications.contains(saved));
	}
}
