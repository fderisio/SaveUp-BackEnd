package saveup.service;

import java.util.List;

import saveup.domain.Category;

public interface CategoryService {

	Category saveCategoryForUser(Category category, Long id);

	Category findById(Long id);

	List<Category> findAll();

	List<Category> findAllByUserId(Long id);

	void deleteById(Long id);

}
