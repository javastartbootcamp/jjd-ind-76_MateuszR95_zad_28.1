package pl.javastart.restoffers.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getCategoryNames() {
        List<String> categoryNameList = categoryService.getCategoryNamesList();
        if (!categoryNameList.isEmpty()) {
            return ResponseEntity.ok(categoryNameList);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryDtoDisplay>> getAllCategories() {
        List<CategoryDtoDisplay> allCategoriesToDisplay = categoryService.getAllCategoriesToDisplay();
        if (!allCategoriesToDisplay.isEmpty()) {
            return ResponseEntity.ok(allCategoriesToDisplay);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("{id}")
                .buildAndExpand(savedCategory.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
