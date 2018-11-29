package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Tutorial;

import repositories.TutorialRepository;

import security.LoginService;
import security.UserAccount;
import utilities.CommonUtilities;

@Service
@Transactional
public class TutorialService {

	// Managed repository..............................................
	@Autowired
	private TutorialRepository tutorialRepository;

	// Supporting services.............................................

	// Simple CRUD METHODS..............

	// browse catalogue of tutorials (not authonticated)
	public Collection<Tutorial> findAll() {

		Collection<Tutorial> tutorials;

		tutorials = this.tutorialRepository.findAll();

		return tutorials;
	}

	// display one tutorial (not authonticated)
	public Tutorial findOne(int tutorialId) {

		Tutorial tutorial;

		tutorial = this.tutorialRepository.findOne(tutorialId);

		return tutorial;
	}

	// showing tutorials of a handy worker
	public Collection<Tutorial> findByHandyWorkerid(int handyWorkerID) {

		Collection<Tutorial> tutorials;

		tutorials = this.tutorialRepository.findByHandyWorkerid(handyWorkerID);

		return tutorials;
	}

	// 49.1 showing tutorials by handy worker principal
	public Collection<Tutorial> findByPrincipal() {
		Collection<Tutorial> tutorials;
		int userAccountId;

		userAccountId = LoginService.getPrincipal().getId();
		tutorials = this.tutorialRepository.findByPrincipal(userAccountId);

		return tutorials;

	}

	public Tutorial SaveTutorial(Tutorial tutorial) {

		UserAccount userAccount;
		boolean isHW;
		userAccount = LoginService.getPrincipal();
		isHW = CommonUtilities.isHandyWorker(userAccount);
		Assert.isTrue(isHW,
				"No puedes crear un warranty porque no eres Handy Worker");

		this.tutorialRepository.save(tutorial);
		return tutorial;
	}

	public void delete(Tutorial tutorial) {

		Assert.notNull(tutorial);
		int userAccountId;

		userAccountId = LoginService.getPrincipal().getId();
		
		Assert.isTrue(
				tutorial.getHandyworker().getUserAccount().getId() == userAccountId,
				"Este tutorial no es suyo para eliminarlo");

		this.tutorialRepository.delete(tutorial);
	}
}
