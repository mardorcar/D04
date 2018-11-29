
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Finder;
import domain.Task;

@Service
@Transactional
public class FinderService {

	@Autowired
	private FinderRepository	finderRepository;


	//	@Autowired
	//	private ActorService		actorService;
	//
	//	@Autowired
	//	private HandyWorkerService	handyWorkerService;

	public Finder create() {
		final Finder finder = new Finder();

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		boolean isHandyWorker = false;

		for (final Authority autoAuthority : userAccount.getAuthorities())
			if (autoAuthority.getAuthority().equals(Authority.HANDYWORKER)) {
				isHandyWorker = true;
				break;
			}
		Assert.isTrue(isHandyWorker, "No puedes crear un finder porque no eres HandyWorker");
		finder.setTask(new ArrayList<Task>());

		return finder;
	}
	public Finder findFinderByHandyWorkerId(final int handyWorkerId) {
		Finder finder;
		finder = this.finderRepository.findByHandyWorkerId(handyWorkerId);
		return finder;
	}
	public Finder findFinderByTaskId(final int TaskId) {
		Finder finder;
		finder = this.finderRepository.findFinderByTaskId(TaskId);

		return finder;
	}

	public Collection<Finder> findAll() {
		Collection<Finder> finders;
		finders = this.finderRepository.findAll();
		//Assert.notNull(finders);
		return finders;
	}

	public Finder save(final Finder finder) {
		//Assert.isTrue(LoginService.getPrincipal().getAuthorities().contains(Authority.HANDYWORKER), "no eres HandyWorker");

		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		boolean isHandyWorker = false;

		for (final Authority autoAuthority : userAccount.getAuthorities())
			if (autoAuthority.getAuthority().equals(Authority.HANDYWORKER)) {
				isHandyWorker = true;
				break;
			}
		Assert.isTrue(isHandyWorker, "No puedes crear un finder porque no eres HandyWorker");

		//		final int idHW = LoginService.getPrincipal().getId();
		//		final HandyWorker hw = this.handyWorkerService.findHandyWorkerByIdFinder(finder.getId());
		//	Assert.isTrue(idHW == hw.getId(), "no puedes modificarlo porque no es su Finder");

		final Finder result = this.finderRepository.save(finder);

		Assert.notNull(result);

		return result;

	}
}
