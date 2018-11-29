
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import domain.Actor;
import domain.Box;
import domain.Message;
import domain.SocialProfile;

@Service
@Transactional
public class ActorService {

	@Autowired
	private ActorRepository	actorRepository;
	@Autowired
	private BoxService		boxService;


	public Actor create() {
		Actor result;
		result = new Actor();
		final String name = new String();
		final String middeName = new String();
		final String surname = new String();
		final String photo = new String();
		final String email = new String();
		final String phoneNumber = new String();
		final String address = new String();
		final Collection<SocialProfile> socialProfiles = new ArrayList<SocialProfile>();
		final Collection<Box> boxes = this.boxService.UndeleteablesBox(result);
		final Collection<Message> messagesReceived = new ArrayList<Message>();
		final Collection<Message> messagesSended = new ArrayList<Message>();
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
	public Actor save(final Actor actor) {
		final Actor result;
		if (actor.getId() != 0) {
			Assert.isTrue(LoginService.getPrincipal().getId() == actor.getUserAccount().getId());
			Assert.notNull(actor.getUserAccount());
		}
		Assert.isTrue(actor.getBoxes().size() >= 4);

		Assert.notNull(actor);
		result = this.actorRepository.save(actor);
		return result;
	}

	public Actor findByPrincipal(final Integer userAccountId) {
		Actor actor;
		actor = this.actorRepository.findActorById(userAccountId);
		return actor;
	}
	Collection<Actor> findActorByBoxId(final int boxId) {
		final Collection<Actor> actorId = this.actorRepository.findActorByBoxId(boxId);
		return actorId;
	}
	public Collection<Actor> findAll() {
		Collection<Actor> result;
		result = this.actorRepository.findAll();
		return result;

	}
}
