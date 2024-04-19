package pl.javastart.restoffers.offer;

import org.springframework.stereotype.Service;
import pl.javastart.restoffers.category.Category;
import pl.javastart.restoffers.category.CategoryRepository;

import java.util.Optional;

@Service
public class OfferDtoMapper {
    private final CategoryRepository categoryRepository;

    public OfferDtoMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public OfferDto mapToDto(Offer offer) {
        OfferDto offerDto = new OfferDto();
        offerDto.setId(offer.getId());
        offerDto.setTitle(offer.getTitle());
        offerDto.setDescription(offer.getDescription());
        offerDto.setPrice(offer.getPrice());
        offerDto.setImgUrl(offer.getImgUrl());
        offerDto.setCategory(offer.getCategory().getName());
        return offerDto;
    }

    public Offer mapToEntity(OfferDto offerDto) {
        Offer offer = new Offer();
        offer.setId(offerDto.getId());
        offer.setTitle(offerDto.getTitle());
        offer.setDescription(offerDto.getDescription());
        offer.setPrice(offerDto.getPrice());
        offer.setImgUrl(offerDto.getImgUrl());
        String categoryName = offerDto.getCategory();
        Optional<Category> categoryOptional = categoryRepository.getCategoryByNameEqualsIgnoreCase(categoryName);
        categoryOptional.ifPresent(offer::setCategory);
        return offer;
    }
}
