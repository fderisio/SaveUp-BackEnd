package saveup.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import saveup.domain.Category;
import saveup.domain.JsonViews;
import saveup.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class RestCategoryController {

	private final CategoryService categoryService;
	
	@Autowired
	public RestCategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping
	public List<Category> retrieveAllCategories() {
		return categoryService.findAll();
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping("/{userId}")
	public List<Category> retrieveCategoriesByUserId(@PathVariable Long userId) {
		return categoryService.findAllByUserId(userId);
	}
	
}
