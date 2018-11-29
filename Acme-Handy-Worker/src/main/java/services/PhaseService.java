
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PhaseRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Phase;

@Service
@Transactional
public class PhaseService {

	@Autowired
	private PhaseRepository	phaseRepository;


	public Phase create() {

		Phase result;
		result = new Phase();
		final String title = new String();
		final String description = new String();
		final Date startMoment = new Date(System.currentTimeMillis());
		final Date endMoment = new Date(System.currentTimeMillis() + 5000);

		result.setDescription(description);
		result.setEndMoment(endMoment);
		result.setStartMoment(startMoment);
		result.setTitle(title);
		return result;

	}

	public Phase save(final Phase phase) {

		final Phase result;
		Assert.notNull(phase);

		if (phase.getId() != 0) {
			UserAccount userAccount;

			userAccount = LoginService.getPrincipal();

			boolean isHandyWorker = false;

			for (final Authority autoAuthority : userAccount.getAuthorities())
				if (autoAuthority.getAuthority().equals(Authority.HANDYWORKER)) {
					isHandyWorker = true;
					break;
				}

			Assert.isTrue(isHandyWorker);
		}
		result = this.phaseRepository.save(phase);
		return result;
	}
	public Collection<Phase> findAll() {
		Collection<Phase> result;
		result = this.phaseRepository.findAll();
		return result;

	}

	public void delete(final Phase phase) {
		Assert.notNull(phase);
		this.phaseRepository.delete(phase);
	}

	public Phase findOne(final int idPhase) {
		Phase phase;
		phase = this.phaseRepository.findOne(idPhase);
		Assert.notNull(phase);
		return phase;
	}

}
