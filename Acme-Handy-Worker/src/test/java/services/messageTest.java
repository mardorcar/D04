package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Message;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class messageTest extends AbstractTest{
	@Autowired
	private MessageService messageService;
	
	@Test
	public void findAll(){
		Collection <Message>all;
		all=messageService.findAll();
		Assert.isTrue(!all.isEmpty());
	}
	@Test
	public void testfindOne() {
	Message message;

		message =messageService.findOne(232);
		
	
		
		Assert.notNull(message);
	}
	@Test
	public void testfindByPrincipal(){
		authenticate("admin2");

		Collection<Message> all;

		all = messageService.findByPrincipal();

		Assert.isTrue(!all.isEmpty());

		authenticate(null);
	}
	@Test
	public void testDelete() {
		authenticate("admin2");

		Message message = messageService.findOne(232);

		messageService.delete(message);
		authenticate(null);
	}
}
