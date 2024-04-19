package pl.javastart.restoffers.category;

import org.springframework.stereotype.Service;
import pl.javastart.restoffers.offer.OfferRepository;

@Service
public class CategoryDtoMapper {

    private final OfferRepository offerRepository;

    public CategoryDtoMapper(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }

    public CategoryDtoDisplay mapToCategoryDtoDisplay(Category category) {
        CategoryDtoDisplay categoryDtoDisplay = new CategoryDtoDisplay();
        categoryDtoDisplay.setName(category.getName());
        categoryDtoDisplay.setDescription(category.getDescription());
        int offersCount = offerRepository.countOfferByCategory(category);
        categoryDtoDisplay.setOffers(offersCount);
        return categoryDtoDisplay;
    }

    public Category mapToEntity(CategoryDto dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }
}
