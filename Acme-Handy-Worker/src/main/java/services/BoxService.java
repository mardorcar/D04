
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BoxRepository;
import security.LoginService;
import domain.Actor;
import domain.Box;
import domain.Message;

@Service
@Transactional
public class BoxService {

	@Autowired
	private BoxRepository	boxRepository;
	@Autowired
	private ActorService	actorService;


	public Box create() {
		final Box result;
		final String name = new String();
		final Collection<Message> messages = new ArrayList<Message>();

		result = new Box();
		result.setName(name);
		result.setDeletable(true);
		result.setMessages(messages);
		return result;
	}
	public Box createSystem(final String name, final Actor actor) {
		Box result;
		final Collection<Message> messages = new ArrayList<Message>();

		result = new Box();
		result.setName(name);
		result.setDeletable(false);
		result.setMessages(messages);
		result = this.saveSystem(result, actor);
		return result;
	}
	public Box save(final Box box) {
		final Box result;
		Assert.notNull(box);
		Assert.isTrue(box.getDeletable());
		result = this.boxRepository.save(box);
		if (box.getId() == 0) {
			final Actor actor;
			actor = this.actorService.findByPrincipal(LoginService.getPrincipal().getId());

			actor.getBoxes().add(result);
			this.actorService.save(actor);

		} else {
			//			final Integer id = this.actorService.findActorByBoxId(box.getId()).getUserAccount().getId();
			//			Assert.isTrue(LoginService.getPrincipal().getId() == id);
		}

		return result;
	}
	public Box saveSystem(final Box box, final Actor actor) {
		Box result;
		result = this.boxRepository.save(box);
		return result;
	}

	public Collection<Box> findAll() {
		Collection<Box> result;
		result = this.boxRepository.findAllByUserAccountId(LoginService.getPrincipal().getId());
		return result;
	}
	//	public void delete(final Box box) {
	//		final Collection<Actor> actor = this.actorService.findActorByBoxId(box.getId());
	//		final Integer id = actor.getUserAccount().getId();
	//		Assert.isTrue(LoginService.getPrincipal().getId() == id);
	//		Assert.isTrue(box.getDeletable());
	//		this.boxRepository.delete(box);
	//		//BORRAR MENSAJES????
	//		this.trashBox(actor).getMessages().addAll(box.getMessages());
	//
	//	}
	//OTROS
	public Collection<Box> UndeleteablesBox(final Actor actor) {
		final Collection<Box> boxes = new ArrayList<Box>();
		boxes.add(this.createSystem("inbox", actor));

		boxes.add(this.createSystem("outbox", actor));

		boxes.add(this.createSystem("trashbox", actor));

		boxes.add(this.createSystem("spambox", actor));
		return boxes;

	}

	public Box trashBox(final Actor actor) {
		final List<Box> boxes = new ArrayList<Box>(actor.getBoxes());
		Box result = null;
		for (int i = 0; i < boxes.size(); i++)
			if ((boxes.get(i).getName() == "trashbox") && (boxes.get(i).getDeletable() == false))
				result = boxes.get(i);
		return result;
	}

}
