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

import saveup.service.CategoryService;
import saveup.service.IncomeService;
import saveup.service.PayMethodService;
import saveup.service.UserService;
import saveup.domain.Category;
import saveup.domain.Income;
import saveup.domain.JsonViews;
import saveup.domain.PayMethod;
import saveup.domain.User;

@RestController
@RequestMapping("/users")
public class RestUserController {

	private final UserService userService;
	private final CategoryService categoryService;
	private final PayMethodService paymethodService;
	private final IncomeService incomeService;
	
	@Autowired
	public RestUserController(UserService userService, CategoryService categoryService,
			PayMethodService paymethodService, IncomeService incomeService) {
		this.userService = userService;
		this.categoryService = categoryService;
		this.paymethodService = paymethodService;
		this.incomeService = incomeService;
		
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping
	public List<User> retrieveAllUsers() {
		return userService.findAll();
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
	
	@JsonView(JsonViews.NewUser.class)
	@PostMapping
	public HttpEntity<Void> registerNewUser(@RequestBody User postedUser) {
		User savedUser = userService.registerNewUser(postedUser);

		UriComponents uriComponents = fromMethodCall(
			on(getClass()).retrieveCategoriesByUserId(savedUser.getId())).build();

		return ResponseEntity.created(uriComponents.encode().toUri()).build();
	}
}
