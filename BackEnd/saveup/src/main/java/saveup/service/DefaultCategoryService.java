package saveup.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import saveup.domain.Category;
import saveup.service.UserService;
import saveup.service.EntityNotFoundException;
import saveup.domain.User;
import saveup.repository.CategoryRepository;

@Service
public class DefaultCategoryService implements CategoryService{

	private static final Logger logger = LoggerFactory.getLogger(DefaultCategoryService.class);

	private final CategoryRepository categoryRepository;
	private final UserService userService;
	
	@Autowired
	public DefaultCategoryService(CategoryRepository categoryRepository, UserService userService) {
		this.categoryRepository = categoryRepository;
		this.userService = userService;
	}
	
	@Transactional(readOnly = false)
	@Override
	public Category saveCategoryForUser(Category category, Long id) {
		logger.trace("Saving category [{}] for user ID [{}].", category, id);

		// Link category to user.
		User user = userService.findById(id);
		user.addCategory(category);

		// Make sure we are saving a new category and not accidentally
		// updating an existing one.
		category.setId(null);

		return categoryRepository.save(category);
	}

	@Override
	public Category findById(Long id) {
		logger.trace("Finding category with ID: {}", id);
		return categoryRepository.findById(id).orElseThrow(
			() -> new EntityNotFoundException("Could not find category with ID [" + id + "]"));
	}
	

	@Override
	public List<Category> findAll() {
		logger.trace("Finding all categories");
		return this.categoryRepository.findAll();
	}

	@Override
	public List<Category> findAllByUserId(Long id) {
		logger.trace("Finding all categories by user ID: {}", id);
		return this.categoryRepository.findAllByUserId(id);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteById(Long id) {
		logger.trace("Finding category with ID: {}", id);
		categoryRepository.deleteById(id).orElseThrow(
			() -> new EntityNotFoundException("Could not find category with ID [" + id + "], "
					+ "or the category is still connected to an expense."));
	}

}
