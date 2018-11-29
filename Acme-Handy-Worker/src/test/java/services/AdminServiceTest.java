
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
import domain.Admin;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AdminServiceTest extends AbstractTest {

	@Autowired
	AdminService	adminService;

	@Autowired
	BoxService		boxService;


	@Test
	public void testSavedAdmin() {

		this.authenticate("admin2");
		Admin admin;
		final Admin saved;

		final Collection<Admin> admins;
		admin = this.adminService.create();

		admin.setName("pepe");
		admin.setMiddeName("dominguez");
		admin.setSurname("antonio");
		admin.setPhoto("https://www.google.es/");
		admin.setEmail("pp@gmail.com");
		admin.setPhoneNumber("+34954446611");
		admin.setAddress("Biblioteca");
		saved = this.adminService.save(admin);
		admins = this.adminService.findAll();
		Assert.isTrue(admins.contains(saved));

	}

}
