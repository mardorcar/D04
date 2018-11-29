
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.CreditCard;
import domain.Customer;
import domain.HandyWorker;
import domain.Task;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	private ApplicationRepository	applicationRepository;
	@Autowired
	private HandyWorkerService		handyWorkerService;
	@Autowired
	private ActorService			actorService;
	@Autowired
	private TaskService				taskService;
	@Autowired
	private CustomerService			customerService;


	public Application create() {
		Application result;
		result = new Application();
		final Date datetime = new Date();
		final CreditCard creditCard = new CreditCard();
		final Collection<String> comments = new ArrayList<String>();
		final Task task = new Task();

		final String holderName = new String();
		final String brandName = new String();
		final String number = new String();
		final String expirationMonth = new String();
		final String expirationYear = new String();
		final Integer CVV = 0;
		creditCard.setHolderName(holderName);
		creditCard.setBrandName(brandName);
		creditCard.setNumber(number);
		creditCard.setExpirationMonth(expirationMonth);
		creditCard.setExpirationYear(expirationYear);
		creditCard.setCVV(CVV);

		result.setCreditCard(creditCard);
		result.setComments(comments);
		result.setStatus("PENDING");
		result.setTask(task);
		result.setDatetime(datetime);
		result.setMaximumPrice(0.0);

		return result;
	}
	public Application save(final Application application) {
		final Application result;
		Assert.notNull(application);
		result = this.applicationRepository.save(application);
		if (application.getStatus() == "ACCEPTED")
			Assert.notNull(application.getCreditCard());
		if (application.getId() == 0) {
			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			boolean isHW = false;

			for (final Authority autoAuthority : userAccount.getAuthorities())
				if (autoAuthority.getAuthority().equals(Authority.HANDYWORKER)) {
					isHW = true;
					break;
				}
			Assert.isTrue(isHW, "No puedes crear un finder porque no eres HandyWorker");
			final HandyWorker handyWorker = this.handyWorkerService.findHWByUAId(LoginService.getPrincipal().getId());
			handyWorker.getApplication().add(result);
			this.handyWorkerService.save(handyWorker);
		} else {
			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			boolean isCustomer = false;

			for (final Authority autoAuthority : userAccount.getAuthorities())
				if (autoAuthority.getAuthority().equals(Authority.CUSTOMER) || autoAuthority.getAuthority().equals(Authority.HANDYWORKER)) {
					isCustomer = true;
					break;
				}
			Assert.isTrue(isCustomer, "No puedes crear un finder porque no eres Customer o HandyWorker");
		}
		return result;

	}
	public Collection<Application> findByCustomerId() {
		final Collection<Application> result;
		//COMPRUEBO SI ES CUSTOMER
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		boolean isCustomer = false;

		for (final Authority autoAuthority : userAccount.getAuthorities())
			if (autoAuthority.getAuthority().equals(Authority.CUSTOMER)) {
				isCustomer = true;
				break;
			}
		Assert.isTrue(isCustomer, "No puedes crear un finder porque no eres Customer");
		final Customer customer = this.customerService.findCustomerById(LoginService.getPrincipal().getId());
		final Integer customerId = customer.getId();
		result = this.applicationRepository.findAllByCustomerId(customerId);
		return result;
	}
	public Collection<Application> findByWhId() {
		final Collection<Application> result;
		//COMPRUEBO SI ES CUSTOMER
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		boolean isHW = false;

		for (final Authority autoAuthority : userAccount.getAuthorities())
			if (autoAuthority.getAuthority().equals(Authority.HANDYWORKER)) {
				isHW = true;
				break;
			}
		Assert.isTrue(isHW, "No puedes crear un finder porque no eres Customer");
		final HandyWorker handyWorker = this.handyWorkerService.findHWByUAId(LoginService.getPrincipal().getId());
		final Integer handyWorkerId = handyWorker.getId();
		result = this.applicationRepository.findAllByHandyWorkerId(handyWorkerId);
		return result;
	}
	public Collection<Application> findAll() {
		Collection<Application> result;
		result = this.applicationRepository.findAll();
		return result;
	}

}
