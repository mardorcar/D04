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

import domain.Support;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class SupportTest extends AbstractTest{
	@Autowired
	private SupportService supportService;
	@Test
	public void findAll() {
		Collection<Support> all;

		all = supportService.findAll();
		Assert.isTrue(!all.isEmpty());

	}
	@Test
	public void testfindOne() {
		Support support;

		support = supportService.findOne(219);
		
	
		
		Assert.notNull(support);

	}
	@Test
	public void testDelete() {
		

		Support support = supportService.findOne(22);

		supportService.delete(support);

	}

}
