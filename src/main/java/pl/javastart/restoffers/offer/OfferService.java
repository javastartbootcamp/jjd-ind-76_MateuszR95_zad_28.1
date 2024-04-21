package pl.javastart.restoffers.offer;

import org.springframework.stereotype.Service;
import pl.javastart.restoffers.category.Category;
import pl.javastart.restoffers.category.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final OfferDtoMapper offerDtoMapper;
    private final CategoryRepository categoryRepository;

    public OfferService(OfferRepository offerRepository, OfferDtoMapper offerDtoMapper, CategoryRepository categoryRepository) {
        this.offerRepository = offerRepository;
        this.offerDtoMapper = offerDtoMapper;
        this.categoryRepository = categoryRepository;
    }

    public List<OfferDto> getAllOffers() {
        return offerRepository.findAllBy().stream().map(offerDtoMapper::mapToDto).toList();
    }

    public int getOffersCount() {
        return offerRepository.countAllBy();
    }

    public List<OfferDto> getFilteredOffersByTitle(String text) {
        if (text == null) {
            return getAllOffers();
        }
        return offerRepository.getOfferByTitleContainsIgnoreCase(text).stream().map(offerDtoMapper::mapToDto).toList();
    }

    public OfferDto saveOffer(OfferDto offerDto) {
        Offer offer = offerDtoMapper.mapToEntity(offerDto);
        String categoryName = offerDto.getCategory();
        Optional<Category> categoryOptional = categoryRepository.getCategoryByNameEqualsIgnoreCase(categoryName);
        categoryOptional.ifPresent(offer::setCategory);
        Offer savedOffer = offerRepository.save(offer);
        return offerDtoMapper.mapToDto(savedOffer);
    }

    public Optional<OfferDto> getOfferById(Long id) {
        return offerRepository.getOfferById(id).map(offerDtoMapper::mapToDto);
    }

    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }
}
