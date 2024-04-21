package pl.javastart.restoffers.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import pl.javastart.restoffers.category.Category;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findAllBy();

    int countAllBy();

    int countOfferByCategory(Category category);

    Optional<Offer> getOfferById(Long id);

    //rozwiązanie problemu n + 1
    @Query("SELECT o FROM Offer o JOIN FETCH o.category WHERE upper(o.title) like concat('%',UPPER(:title),'%') ")
    List<Offer> getOfferByTitleContainsIgnoreCase(@Param("title") String title);
}
