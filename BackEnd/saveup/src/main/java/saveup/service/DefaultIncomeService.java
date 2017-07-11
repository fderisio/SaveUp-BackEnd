package saveup.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import saveup.domain.Income;
import saveup.domain.User;
import saveup.repository.IncomeRepository;

@Service
public class DefaultIncomeService implements IncomeService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultUserService.class);
	
	private final IncomeRepository incomeRepository;
	private final UserService userService;
	
	@Autowired
	public DefaultIncomeService(IncomeRepository incomeRepository, UserService userService) {
		this.incomeRepository = incomeRepository;
		this.userService = userService;
	}
	
	@Transactional(readOnly = false)
	@Override
	public void saveIncomeForUser(Income income, Long id) {
		logger.trace("Saving income [{}] for user [{}].", income, id);
		
		// link income to user
		User user = userService.findById(id);
		income.setUser(user);
		
		// Make sure we are saving a new income and not accidentally
		// updating an existing one
		income.setId(null);

		incomeRepository.save(income);
		
		// Link user to income
		user.addIncome(income);
	}
	
	@Override
	public Income findById(Long id) {
		logger.trace("Finding income with ID: {}", id);
		return incomeRepository.findById(id).orElseThrow(
			() -> new EntityNotFoundException("Could not find income with ID [" + id + "]"));
	}

	@Override
	public List<Income> findAll() {
		logger.trace("Finding all income");
		return this.incomeRepository.findAll();
	}

	@Override
	public List<Income> findAllByUserId(Long id) {
		logger.trace("Finding all income by user ID: {}", id);
		return this.incomeRepository.findByUserId(id);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void deleteById(Long id) {
		logger.trace("Deleting income with ID [{}].", id);
		incomeRepository.delete(id);
	}

}
