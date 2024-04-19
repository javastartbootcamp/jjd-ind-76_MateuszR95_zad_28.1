package pl.javastart.restoffers.offer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.javastart.restoffers.category.Category;
import pl.javastart.restoffers.category.CategoryService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    private final OfferService offerService;
    private final CategoryService categoryService;


    public OfferController(OfferService offerService, CategoryService categoryService) {
        this.offerService = offerService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<OfferDto>> getFilteredOffersByTitle(@RequestParam(required = false) String title) {
        List<OfferDto> filteredOffersByTitle = offerService.getFilteredOffersByTitle(title);
        if (!filteredOffersByTitle.isEmpty()) {
            return ResponseEntity.ok(filteredOffersByTitle);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public int getOffersCount() {
        return offerService.getOffersCount();
    }

    @PostMapping
    public ResponseEntity<OfferDto> saveOffer(@RequestBody OfferDto offerDto) {
        Optional<Category> categoryByName = categoryService.getCategoryByName(offerDto.getCategory());
        if (categoryByName.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        OfferDto savedOffer = offerService.saveOffer(offerDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("{id}")
                .buildAndExpand(savedOffer.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedOffer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> getOfferById(@PathVariable Long id) {
        Optional<OfferDto> offerOptional = offerService.getOfferById(id);
        return offerOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOfferById(@PathVariable Long id) {
        offerService.deleteOffer(id);
        return ResponseEntity.noContent().build();

    }


}
