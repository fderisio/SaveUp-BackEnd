package saveup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import saveup.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	Optional<Category> findById(Long id);
	
}
