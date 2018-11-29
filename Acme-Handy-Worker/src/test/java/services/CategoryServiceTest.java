
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Category;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CategoryServiceTest extends AbstractTest {

	@Autowired
	private CategoryService	categoryService;


	@Test
	public void testSaveCategory() {
		this.authenticate("Admin2");
		final Category category;
		final Category saved;

		final Collection<Category> categories;
		category = this.categoryService.create();

		final Category categoryFather = this.categoryService.findOne(581);

		category.setFather(categoryFather);
		category.setName("Category3");

		saved = this.categoryService.save(category);
		categories = this.categoryService.findAll();
		Assert.isTrue(categories.contains(saved));

	}

}
