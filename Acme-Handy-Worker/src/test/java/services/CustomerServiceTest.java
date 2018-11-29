
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Complaint;
import domain.Customer;
import domain.Endorse;
import domain.Note;
import domain.Task;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CustomerServiceTest extends AbstractTest {

	@Autowired
	private CustomerService	customerService;


	@Test
	public void CustomerSaveTest() {
		final Customer customer;
		final Customer save;
		final Collection<Customer> customers;

		customer = this.customerService.create();

		final Collection<Complaint> complaints = new ArrayList<Complaint>();
		final Collection<Note> notes = new ArrayList<Note>();
		final Collection<Endorse> endorseS = new ArrayList<Endorse>();
		final Collection<Endorse> endorseR = new ArrayList<Endorse>();
		final Collection<Task> tasks = new ArrayList<Task>();

		customer.setName("Pepe");
		customer.setMiddeName("Raimundo");
		customer.setSurname("Amador");
		customer.setPhoto("https://www.google.es/");
		customer.setEmail("pp@gmail.com");
		customer.setPhoneNumber("+34954446611");
		customer.setAddress("Biblioteca");
		customer.setComplaints(complaints);
		customer.setNotes(notes);
		customer.setEndorseReceiver(endorseR);
		customer.setEndorseSender(endorseS);
		customer.setTasks(tasks);

		save = this.customerService.save(customer);

		customers = this.customerService.findAll();
		Assert.isTrue(customers.contains(save));
		//super.authenticate(null);

	}
}
