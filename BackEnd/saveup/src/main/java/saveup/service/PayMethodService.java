package saveup.service;

import java.util.List;

import saveup.domain.PayMethod;

public interface PayMethodService {

	PayMethod savePayMethodForUser(PayMethod paymethod, String username);

	PayMethod findById(Long id);

	List<PayMethod> findAll();

	List<PayMethod> findAllByUsername(String username);

	List<PayMethod> findAllContainingText(String searchText);
	
}
