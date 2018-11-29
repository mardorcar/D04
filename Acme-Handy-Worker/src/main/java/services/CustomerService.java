
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Box;
import domain.Complaint;
import domain.Customer;
import domain.Endorse;
import domain.Task;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository	customerRepository;

	@Autowired
	private BoxService			boxService;


	public Customer create() {
		final Customer customer = new Customer();

		final Authority authority = new Authority();
		authority.setAuthority(Authority.CUSTOMER);

		final Collection<Authority> authorities = new ArrayList<>();
		authorities.add(authority);

		final Collection<Box> boxes = this.boxService.UndeleteablesBox(customer);

		final UserAccount userAccount = new UserAccount();
		userAccount.setAuthorities(authorities);

		customer.setBoxes(boxes);
		customer.setComplaints(new ArrayList<Complaint>());
		customer.setEndorseReceiver(new ArrayList<Endorse>());
		customer.setEndorseSender(new ArrayList<Endorse>());
		customer.setTasks(new ArrayList<Task>());

		Assert.notNull(customer);
		return customer;
	}

	public Customer findCustomerById(final int customerId) {
		Customer result;
		result = this.customerRepository.findOne(customerId);
		//Assert.notNull(result);

		return result;
	}

	public Customer save(final Customer customer) {
		final Customer result;
		if (customer.getId() != 0) {
			Assert.isTrue(LoginService.getPrincipal().getId() == customer.getUserAccount().getId());
			Assert.notNull(customer.getUserAccount());
		}
		Assert.isTrue(customer.getBoxes().size() >= 4);

		Assert.notNull(customer);
		result = this.customerRepository.save(customer);
		return result;
	}
	public Collection<Customer> findAll() {
		Collection<Customer> customers;
		customers = this.customerRepository.findAll();
		return customers;

	}

}
