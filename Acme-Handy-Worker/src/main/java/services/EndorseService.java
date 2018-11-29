package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Endorse;

import repositories.EndorseRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.CommonUtilities;

@Service
@Transactional
public class EndorseService {

	// Managed repository..............................................
	@Autowired
	private EndorseRepository endorseRepository;
	
	// Simple CRUD METHODS..............

		public Endorse findyid(int id) {
		Endorse endorse;
		endorse = endorseRepository.findOne(id);
		return endorse;
	}
		public Endorse saveEndorse(Endorse endorse) {

			UserAccount userAccount;
			boolean isCustomer;
			userAccount = LoginService.getPrincipal();
			isCustomer = CommonUtilities.isCustomer(userAccount);
			Assert.isTrue(isCustomer,
					"No puedes crear un warranty porque no eres Customer");

			this.endorseRepository.save(endorse);

			return endorse;
		}
		public void delete(Endorse endorse) {

			Assert.notNull(endorse);

			int userAccountId;

			userAccountId = LoginService.getPrincipal().getId();

			Assert.isTrue(
					endorse.getHandyWorkerReceiver().getUserAccount().getId() != userAccountId,
					"Este tutorial no es suyo para eliminarlo");

			this.endorseRepository.delete(endorse);
		}
		//Other Business methods..................

	public Collection<Endorse> findByCustomerUserAccount() {

		Collection<Endorse> endorses;

		int userAccountId;

		userAccountId = LoginService.getPrincipal().getId();

		endorses = this.endorseRepository
				.findByCustomerUserAccountid(userAccountId);

		return endorses;
	}	

	public Collection<Endorse> findByPrincipalHWR() {

		Collection<Endorse> endorses;

		int userAccountId;

		userAccountId = LoginService.getPrincipal().getId();

		endorses = this.endorseRepository.findByUserHWRAccountid(userAccountId);

		return endorses;

	}

	public Collection<Endorse> findByPrincipalHWS() {

		Collection<Endorse> endorses;

		int userAccountId;

		userAccountId = LoginService.getPrincipal().getId();

		endorses = this.endorseRepository.findByUserHWSAccountid(userAccountId);

		return endorses;

	}

}
