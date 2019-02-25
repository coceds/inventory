package capstone.inventory.repository;

import capstone.inventory.dto.ProductAvailability;
import capstone.inventory.manager.ProductLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Repository
public class InventoryRepository {

    private final Map<String, ProductAvailability> availabilityMap;

    @Autowired
    public InventoryRepository(ProductLoader productLoader) {
        final List<String> productIds = productLoader.loadProducts();
        final Supplier<Integer> randomQuantity = () -> {
            final Random random = new Random();
            return random.nextInt(100);
        };

        Map<String, ProductAvailability> map = productIds.stream()
                .map(p -> new ProductAvailability(p, randomQuantity.get()))
                .collect(Collectors.toMap(ProductAvailability::getProductId, Function.identity()));

        this.availabilityMap = Collections.unmodifiableMap(map);
    }

    public List<ProductAvailability> checkAvailability(List<String> productIds) {
        return productIds.stream()
                .map(p -> Optional.ofNullable(availabilityMap.get(p)).orElse(new ProductAvailability(p, -1)))
                .collect(Collectors.toList());
    }

}
