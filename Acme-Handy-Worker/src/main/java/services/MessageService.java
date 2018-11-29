
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Message;


import repositories.MessageRepository;
import security.LoginService;
import security.UserAccount;
import utilities.CommonUtilities;

@Service
@Transactional
public class MessageService {

	//Managed repository..............................................

	@Autowired
	private MessageRepository	messageRepository;

	//Supporting services.............................................
	
	public Collection<Message> findAll() {

		Collection<Message> messages;

		messages = this.messageRepository.findAll();

		return messages;
	} 
	public Message findOne(int id){
		Message message;
		message=this.messageRepository.findOne(id);
		return message;
		}
	public Collection<Message> findByPrincipal() {
		Collection<Message> messages;
		int userAccountId;

		userAccountId = LoginService.getPrincipal().getId();
		messages = this.findByPrincipal();

		return messages;
	}
	public void delete(Message message){

		Assert.notNull(message);
		int userAccountId;

		userAccountId = LoginService.getPrincipal().getId();

		Assert.isTrue(
				((message.getReceiver().getUserAccount().getId() == userAccountId)||(message.getReceiver().getUserAccount().getId() == userAccountId)),
				"Este mensaje no es suyo para eliminarlo");

		this.messageRepository.delete(message);
		
	}
	public Message SaveMessage(Message message) {

		this.messageRepository.save(message);
		return  message;
	}

}
