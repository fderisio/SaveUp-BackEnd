package saveup.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import saveup.domain.Expense;
import saveup.domain.JsonViews;
import saveup.service.ExpenseService;

@RestController
@RequestMapping("/expenses")
public class RestExpenseController {

	private final ExpenseService expenseService;
	
	@Autowired
	public RestExpenseController(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping
	public List<Expense> retrieveAllCategories() {
		return expenseService.findAll();
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping("/{userId}")
	public List<Expense> retrieveCategoriesByUserId(@PathVariable Long categoryId) {
		return expenseService.findAllByCategoryId(categoryId);
	}
	
}
