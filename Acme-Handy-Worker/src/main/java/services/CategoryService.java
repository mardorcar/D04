
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Category;
import domain.Task;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository	categoryRepository;

	@Autowired
	private TaskService			taskService;


	public Category create() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		boolean isAdmin = false;

		for (final Authority autoAuthority : userAccount.getAuthorities())
			if (autoAuthority.getAuthority().equals(Authority.ADMIN)) {
				isAdmin = true;
				break;
			}
		Assert.isTrue(isAdmin, "No puedes crear un finder porque no eres Customer");

		final String name = new String();
		final Category result = new Category();
		final Category father = new Category();
		result.setFather(father);
		result.setName(name);
		return result;

	}
	public Category save(final Category category) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		boolean isAdmin = false;

		for (final Authority autoAuthority : userAccount.getAuthorities())
			if (autoAuthority.getAuthority().equals(Authority.ADMIN)) {
				isAdmin = true;
				break;
			}
		Assert.isTrue(isAdmin, "No puedes crear un finder porque no eres Customer");
		Category result;
		Assert.notNull(category);
		result = this.categoryRepository.save(category);
		return result;

	}
	public Collection<Category> findAll() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		boolean isAdmin = false;

		for (final Authority autoAuthority : userAccount.getAuthorities())
			if (autoAuthority.getAuthority().equals(Authority.ADMIN)) {
				isAdmin = true;
				break;
			}
		Assert.isTrue(isAdmin, "No puedes crear un finder porque no eres Customer");
		Collection<Category> result;
		result = this.categoryRepository.findAll();
		return result;
	}
	public Category findOne(final int CategoryId) {
		Category result;
		result = this.categoryRepository.findOne(CategoryId);
		return result;
	}
	//CAMBIAR PARA BORRAR PADRE Y TASKS
	public void delete(final Category category) {
		Assert.isNull(category);
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		boolean isAdmin = false;

		for (final Authority autoAuthority : userAccount.getAuthorities())
			if (autoAuthority.getAuthority().equals(Authority.ADMIN)) {
				isAdmin = true;
				break;
			}
		Assert.isTrue(isAdmin, "No puedes crear un finder porque no eres Customer");
		Assert.isTrue(category.getName() == "CATEGORY", "no puedes borrarla porque es una categoria padre");

		final Category father = this.findOne(581);
		final Collection<Task> categoriesSueltas = this.taskService.findTaskByIdCategory(category.getId());
		for (final Task t : categoriesSueltas)
			t.getCategory().setFather(father);

		this.categoryRepository.delete(category);
	}
}
