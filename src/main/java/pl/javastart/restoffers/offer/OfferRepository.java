package pl.javastart.restoffers.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javastart.restoffers.category.Category;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findAllBy();

    int countAllBy();

    int countOfferByCategory(Category category);

    Optional<Offer> getOfferById(Long id);
}
