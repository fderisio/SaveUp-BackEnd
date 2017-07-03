package saveup.service;

import java.util.List;

import saveup.domain.Category;

public interface CategoryService {

	Category saveCategoryForUser(Category category, String username);

	Category findById(Long id);

	List<Category> findAll();

	List<Category> findAllByUsername(String username);

	List<Category> findAllContainingText(String searchText);
	
}
