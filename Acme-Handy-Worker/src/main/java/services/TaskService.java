
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TaskRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.Category;
import domain.Complaint;
import domain.Customer;
import domain.HandyWorker;
import domain.Phase;
import domain.Task;
import domain.Warranty;

@Service
@Transactional
public class TaskService {

	@Autowired
	private TaskRepository		taskRepository;
	@Autowired
	private CustomerService		customerService;

	//	@Autowired
	//	private FinderService		finderService;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	public Task create() {

		final Task task = new Task();

		final int customerId = LoginService.getPrincipal().getId();

		Customer customer;
		customer = this.customerService.findCustomerById(customerId);

		task.setCustomer(customer);
		task.setApplications(new ArrayList<Application>());
		task.setComplaints(new ArrayList<Complaint>());
		task.setPhases(new ArrayList<Phase>());

		final Warranty warranty = new Warranty();
		final Category category = new Category();
		task.setWarranty(warranty);
		task.setCategory(category);

		final String ticket = new String();
		task.setTicket(ticket);
		final Date moment = new Date();
		task.setMoment(moment);
		final String address = new String();
		task.setAddress(address);
		final Double maximumPrice = new Double(1000.);
		task.setMaximumPrice(maximumPrice);
		final Date startDate = new Date();
		task.setStartDate(startDate);
		final Date endDate = new Date();
		task.setStartDate(endDate);

		Assert.notNull(task);

		return task;

	}
	public Collection<Task> findTasksByIdCustomer(final int customerId) {
		Collection<Task> task = new ArrayList<Task>();
		task = this.taskRepository.findTasksByIdCustomer(customerId);
		Assert.notNull(task);

		return task;
	}

	public Collection<Task> findTasksByIdFinder(final int FinderId) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		boolean isHW = false;

		for (final Authority autoAuthority : userAccount.getAuthorities()) {
			if (autoAuthority.getAuthority().equals(Authority.HANDYWORKER)) {
				isHW = true;
				break;
			}
			Assert.isTrue(isHW, "No puedes crear un finder porque no eres Customer");
		}

		final int idHW = LoginService.getPrincipal().getId();
		final HandyWorker hw = this.handyWorkerService.findHandyWorkerByIdFinder(FinderId);
		Assert.isTrue(idHW == hw.getId(), "no puedes visualizar las task porque no es su Finder");

		Collection<Task> task = new ArrayList<Task>();
		task = this.taskRepository.findTasksByIdFinder(FinderId);
		Assert.notNull(task);

		return task;
	}
	public Collection<Task> findAll() {
		Collection<Task> tasks = new ArrayList<>();

		tasks = this.taskRepository.findAll();
		Assert.notNull(tasks);
		return tasks;
	}

	public Task findOne(final int TaskId) {
		Task task;
		task = this.taskRepository.findOne(TaskId);
		Assert.notNull(task);

		return task;
	}
	public Task save(final Task task) {

		final Task result = this.taskRepository.save(task);
		if (task.getId() == 0) {

			UserAccount userAccount;
			userAccount = LoginService.getPrincipal();
			boolean isCustomer = false;

			for (final Authority autoAuthority : userAccount.getAuthorities())
				if (autoAuthority.getAuthority().equals(Authority.CUSTOMER)) {
					isCustomer = true;
					break;
				}
			Assert.isTrue(isCustomer, "No puedes crear un finder porque no eres Customer");
			//Assert.isTrue(LoginService.getPrincipal().getAuthorities().contains(Authority.CUSTOMER));
		}
		final Customer customer = this.customerService.findCustomerById(task.getCustomer().getId());
		customer.getTasks().add(result);
		this.customerService.save(customer);

		//		final Finder finder = this.finderService.findFinderByTaskId(task.getId());
		//		finder.getTask().add(result);
		//		this.finderService.save(finder);

		Assert.notNull(result);

		return result;

	}
	public Collection<Task> findTaskByIdCategory(final int id) {
		Collection<Task> result;
		result = this.taskRepository.findTaskByIdCategory(id);

		return result;
	}
}
