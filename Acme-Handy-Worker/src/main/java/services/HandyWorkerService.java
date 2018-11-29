
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.HandyWorkerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Box;
import domain.HandyWorker;
import domain.Message;
import domain.SocialProfile;

@Service
@Transactional
public class HandyWorkerService {

	@Autowired
	HandyWorkerRepository	handyWorkerRepository;

	@Autowired
	BoxService				boxService;


	public HandyWorker create() {

		HandyWorker result;
		result = new HandyWorker();
		final String name = new String();
		final String middeName = new String();
		final String surname = new String();
		final String photo = new String();
		final String email = new String();
		final String phoneNumber = new String();
		final String address = new String();
		final String make = new String();
		final UserAccount cuenta = new UserAccount();
		final List<Authority> autoridades = new ArrayList<Authority>();
		final Authority authority = new Authority();
		final Collection<SocialProfile> socialProfiles = new ArrayList<SocialProfile>();
		final Collection<Box> boxes = this.boxService.UndeleteablesBox(result);
		final Collection<Message> messagesReceived = new ArrayList<Message>();
		final Collection<Message> messagesSended = new ArrayList<Message>();

		authority.setAuthority(Authority.HANDYWORKER);
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
		result.setMake(make);

		return result;

	}

	public HandyWorker save(final HandyWorker handyWorker) {
		final HandyWorker result;
		if (handyWorker.getId() != 0) {
			Assert.isTrue(LoginService.getPrincipal().getId() == handyWorker.getUserAccount().getId());
			Assert.notNull(handyWorker.getUserAccount());
		}
		//Assert.isTrue(handyWorker.getBoxes().size() >= 4);

		Assert.notNull(handyWorker);
		result = this.handyWorkerRepository.save(handyWorker);
		return result;
	}

	public Collection<HandyWorker> findAll() {
		final Collection<HandyWorker> handyWorkers;
		handyWorkers = this.handyWorkerRepository.findAll();
		return handyWorkers;
	}

	public HandyWorker findHandyWorkerByIdFinder(final int finderId) {
		final HandyWorker handyWorker;
		handyWorker = this.handyWorkerRepository.findHandyWorkerByIdFinder(finderId);
		return handyWorker;
	}
	public HandyWorker findHWByUAId(final int uaId) {
		final HandyWorker handyWorker;
		handyWorker = this.handyWorkerRepository.findHandyWorkerByUAId(uaId);
		return handyWorker;
	}
	public HandyWorker findById(final int id) {
		final HandyWorker handyWorker;
		handyWorker = this.handyWorkerRepository.findOne(id);
		return handyWorker;
	}

}
