package saveup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import saveup.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	Optional<Category> findById(Long id);
	
	List<Category> findAllByUserId(Long id);

	Optional<Category> deleteById(Long id);
	
}
