package saveup.service;

import java.util.List;

import saveup.domain.PayMethod;

public interface PayMethodService {

	void savePayMethodForUser(PayMethod paymethod, Long id);

	PayMethod findById(Long id);

	List<PayMethod> findAll();

	List<PayMethod> findAllByUserId(Long id);

	PayMethod update(PayMethod paymethod);
	
	void deleteById(Long id);
}
