package saveup.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import saveup.domain.Income;
import saveup.domain.JsonViews;
import saveup.service.IncomeService;

@RestController
@RequestMapping("/income")
public class RestIncomeController {

	private final IncomeService incomeService;
	
	@Autowired
	public RestIncomeController(IncomeService incomeService) {
		this.incomeService = incomeService;
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping
	public List<Income> retrieveAllCategories() {
		return incomeService.findAll();
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping("/{userId}")
	public List<Income> retrieveCategoriesByUserId(@PathVariable Long userId) {
		return incomeService.findAllByUserId(userId);
	}
	
}
