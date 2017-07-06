package saveup.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import saveup.domain.JsonViews;
import saveup.domain.PayMethod;
import saveup.service.PayMethodService;

@RestController
@RequestMapping("/paymethods")
public class RestPayMethodController {

	private final PayMethodService paymethodService;
	
	@Autowired
	public RestPayMethodController(PayMethodService paymethodService) {
		this.paymethodService = paymethodService;
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping
	public List<PayMethod> retrieveAllPayMethods() {
		return paymethodService.findAll();
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping("/{userId}")
	public List<PayMethod> retrieveCategoriesByUserId(@PathVariable Long userId) {
		return paymethodService.findAllByUserId(userId);
	}
	
}
