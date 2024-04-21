package pl.javastart.restoffers.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllBy();

    Optional<Category> getCategoryByNameEqualsIgnoreCase(String name);


}
