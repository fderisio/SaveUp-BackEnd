package saveup.web;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;

import com.fasterxml.jackson.annotation.JsonView;

import saveup.domain.Category;
import saveup.domain.Expense;
import saveup.domain.Income;
import saveup.domain.JsonViews;
import saveup.domain.PayMethod;
import saveup.domain.User;
import saveup.service.CategoryService;
import saveup.service.ExpenseService;
import saveup.service.IncomeService;
import saveup.service.PayMethodService;
import saveup.service.UserService;

@RestController
@RequestMapping("/user")
public class RestUserController {

	private final UserService userService;
	private final CategoryService categoryService;
	private final PayMethodService paymethodService;
	private final IncomeService incomeService;
	private final ExpenseService expenseService;
	
	@Autowired
	public RestUserController(UserService userService, CategoryService categoryService,
			PayMethodService paymethodService, IncomeService incomeService,
			ExpenseService expenseService) {
		this.userService = userService;
		this.categoryService = categoryService;
		this.paymethodService = paymethodService;
		this.incomeService = incomeService;
		this.expenseService = expenseService;
		
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping("/{userId}")
	public User retrieveUserById(@PathVariable Long userId) {
		return userService.findById(userId);
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping("/{userId}/categories")
	public List<Category> retrieveCategoriesByUserId(@PathVariable Long userId) {
		return categoryService.findAllByUserId(userId);
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping("/{userId}/paymethods")
	public List<PayMethod> retrievePayMethodByUserId(@PathVariable Long userId) {
		return paymethodService.findAllByUserId(userId);
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping("/{userId}/incomes")
	public List<Income> retrieveIncomeByUserId(@PathVariable Long userId) {
		return incomeService.findAllByUserId(userId);
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping("/{userId}/expenses")
	public List<Expense> retrieveAllExpenses(@PathVariable Long userId) {
		return expenseService.retrieveAllExpensesForUser(userId);
	}
	
	@JsonView(JsonViews.Public.class)
	@PostMapping("/login")
	public HttpEntity<Void> postUserByEmailAndPassword(@RequestBody User postedUser) {
		String email = postedUser.getEmail();
		String password = postedUser.getPassword();
		User user = userService.findByEmailAndPassword(email, password);
		
		UriComponents uriComponents = fromMethodCall(
				on(getClass()).retrieveUserById(user.getId())).build();
		
		return ResponseEntity.created(uriComponents.encode().toUri()).build();
	}
	
	@JsonView(JsonViews.Public.class)
	@PostMapping("/signup")
	public HttpEntity<Void> registerNewUser(@RequestBody User postedUser) {
		User savedUser = userService.registerNewUser(postedUser);

		UriComponents uriComponents = fromMethodCall(
			on(getClass()).retrieveCategoriesByUserId(savedUser.getId())).build();

		return ResponseEntity.created(uriComponents.encode().toUri()).build();
	}
	
//	@JsonView(JsonViews.Public.class)
//	@PostMapping("/1/expenses/add")
//	public void createExpense(@RequestBody Expense postedExpense) {
//		Category category = categoryService.findById(postedExpense.getCategory().getId());
//		PayMethod paymethod = paymethodService.findById(postedExpense.getPayMethod().getId());
//		expenseService.registerNewExpense(postedExpense, category, paymethod);
//	}
	
	@JsonView(JsonViews.Public.class)
	@PostMapping("/1/expenses/add")
	public void createExpense(@RequestBody Expense postedExpense) {
		Category category = postedExpense.getCategory();
		PayMethod paymethod = postedExpense.getPayMethod();
		expenseService.save(postedExpense, category, paymethod);
	}
	
	@JsonView(JsonViews.Public.class)
	@PostMapping("/1/categories/add") // in the future: @PostMapping("/{userId}/categories/add")
	public void createCategory(@RequestBody Category postedCategory) {
		categoryService.saveCategoryForUser(postedCategory, 1L);
	}

}
