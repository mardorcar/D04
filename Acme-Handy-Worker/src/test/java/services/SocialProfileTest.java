
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

import domain.SocialProfile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class SocialProfileTest {

	@Autowired
	SocialProfileService	socialProfileService;
	@Autowired
	CustomerService			customerService;


	@Test
	public void testfindOne() {
		SocialProfile socialProfile;
		socialProfile = this.socialProfileService.findOne(229);
		Assert.notNull(socialProfile);
	}
	@Test
	public void testfindAll() {
		Collection<SocialProfile> all;
		all = this.socialProfileService.findAll();
		Assert.isTrue(all.isEmpty());
	}
	@Test
	public void testdeleteSocialProfile() {
		final SocialProfile socialProfile = this.socialProfileService.findOne(263);
		this.socialProfileService.delete(socialProfile);
	}
	@Test
	public void testFindByActor() {
		SocialProfile socialProfile;
		socialProfile = this.socialProfileService.findByActor(123);
		Assert.notNull(socialProfile);
	}
	@Test
	public void testSaveSocialProfile() {
		final SocialProfile socialProfile = new SocialProfile();

		socialProfile.setActor(this.customerService.findCustomerById(614));
		socialProfile.setNick("NICK 1");
		socialProfile.setLink("https://drive.google.com/drive/");
		socialProfile.setSocialNetwork("social Network 1");
		this.socialProfileService.SaveSocialProfile(socialProfile);

	}

}
