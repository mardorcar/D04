
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdminRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Admin;
import domain.Box;
import domain.Message;
import domain.SocialProfile;

@Service
@Transactional
public class AdminService {

	@Autowired
	private AdminRepository	adminRepository;

	@Autowired
	private BoxService		boxService;


	public Admin create() {
		Admin result;
		result = new Admin();
		final String name = new String();
		final String middeName = new String();
		final String surname = new String();
		final String photo = new String();
		final String email = new String();
		final String phoneNumber = new String();
		final String address = new String();
		final UserAccount cuenta = new UserAccount();
		final List<Authority> autoridades = new ArrayList<Authority>();
		final Authority authority = new Authority();
		final Collection<SocialProfile> socialProfiles = new ArrayList<SocialProfile>();
		final Collection<Box> boxes = this.boxService.UndeleteablesBox(result);
		final Collection<Message> messagesReceived = new ArrayList<Message>();
		final Collection<Message> messagesSended = new ArrayList<Message>();

		authority.setAuthority(Authority.ADMIN);
		autoridades.add(authority);
		cuenta.setAuthorities(autoridades);
		result.setName(name);
		result.setMiddeName(middeName);
		result.setSurname(surname);
		result.setPhoto(photo);
		result.setEmail(email);
		result.setPhoneNumber(phoneNumber);
		result.setAddress(address);
		result.setMessagesSended(messagesSended);
		result.setMessagesReceived(messagesReceived);
		result.setSocialProfiles(socialProfiles);
		result.setBoxes(boxes);

		return result;
	}
	public Admin save(final Admin admin) {
		final Admin result;
		if (admin.getId() != 0) {
			Assert.isTrue(LoginService.getPrincipal().getId() == admin.getUserAccount().getId());
			Assert.notNull(admin.getUserAccount());
		}
		Assert.isTrue(admin.getBoxes().size() >= 4);

		Assert.notNull(admin);
		result = this.adminRepository.save(admin);
		return result;
	}
	//		final Admin result;
	//		
	//		Assert.notNull(admin.getUserAccount());
	//		Assert.notNull(admin);
	//		Assert.isTrue(LoginService.getPrincipal().getAuthorities().contains(Authority.ADMIN));
	//		result = this.adminRepository.save(admin);
	//		return result;
	//	}

	public Collection<Admin> findAll() {
		final Collection<Admin> admins;
		admins = this.adminRepository.findAll();
		return admins;
	}
	//	public Actor findOne(final int adminId) {
	//		final Admin admin;
	//		admin = this.adminRepository.findOne(adminId);
	//		return admin;
	//	}
	//	public void delete(final Admin admin) {
	//		Assert.notNull(admin);
	//		this.adminRepository.delete(admin);
	//	}
}
