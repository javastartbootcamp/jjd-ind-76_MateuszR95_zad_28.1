package pl.javastart.restoffers.category;

import org.springframework.stereotype.Service;
import pl.javastart.restoffers.offer.OfferRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryDtoMapper categoryDtoMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    public List<String> getCategoryNamesList() {
        List<Category> allCategories = categoryRepository.findAllBy();
        return allCategories.stream().map(Category::getName).collect(Collectors.toList());
    }

    public List<CategoryDtoDisplay> getAllCategoriesToDisplay() {
        List<Category> allCategories = categoryRepository.findAllBy();
        return allCategories.stream().map(categoryDtoMapper::mapToCategoryDtoDisplay).toList();
    }

    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.getCategoryByNameEqualsIgnoreCase(name);
    }

    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = categoryDtoMapper.mapToEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryDtoMapper.mapToDto(savedCategory);
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
