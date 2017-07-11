package saveup.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import saveup.domain.PayMethod;
import saveup.domain.User;
import saveup.repository.PayMethodRepository;

@Transactional(readOnly = true)
@Service
public class DefaultPayMethodService implements PayMethodService{

	private static final Logger logger = LoggerFactory.getLogger(DefaultUserService.class);
	
	private final PayMethodRepository payMethodRepository;
	private final UserService userService;
	
	@Autowired
	public DefaultPayMethodService(PayMethodRepository payMethodRepository, UserService userService) {
		this.payMethodRepository = payMethodRepository;
		this.userService = userService;
	}
	
	@Transactional(readOnly = false)
	@Override
	public void savePayMethodForUser(PayMethod paymethod, Long id) {
		logger.trace("Saving payment method [{}] for user [{}].", paymethod, id);

		// Search for user
		User user = userService.findById(id);

		// Make sure we are saving a new payment method and not accidentally
		// updating an existing one.
		paymethod.setId(null);

		// link user to payment method
		paymethod.setUser(user);
		
		// create payment method
		payMethodRepository.save(paymethod);
		
		// Link payment method to user
		user.addPayMethod(paymethod);	
	}

	@Transactional(readOnly = false)
	@Override
	public PayMethod update(PayMethod paymethod) {
		logger.trace("Updating payment method [{}].", paymethod);
		return payMethodRepository.save(paymethod);
	}
	@Override
	public PayMethod findById(Long id) {
		logger.trace("Finding payment method with ID: {}", id);
		return payMethodRepository.findById(id).orElseThrow(
			() -> new EntityNotFoundException("Could not find payment method with ID [" + id + "]"));
	}

	@Override
	public List<PayMethod> findAll() {
		logger.trace("Finding all payment methods");
		return this.payMethodRepository.findAll();
	}

	@Override
	public List<PayMethod> findAllByUserId(Long id) {
		logger.trace("Finding all payment methods by user ID: {}", id);
		return this.payMethodRepository.findByUserId(id);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void deleteById(Long id) {
		logger.trace("Deleting payment method with ID [{}].", id);
		payMethodRepository.delete(id);
	}

}
