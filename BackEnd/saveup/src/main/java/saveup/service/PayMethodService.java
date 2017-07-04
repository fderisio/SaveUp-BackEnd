package saveup.service;

import java.util.List;

import saveup.domain.PayMethod;

public interface PayMethodService {

	PayMethod savePayMethodForUser(PayMethod paymethod, Long id);

	PayMethod findById(Long id);

	List<PayMethod> findAll();

	List<PayMethod> findAllByUserId(Long id);
	
}
