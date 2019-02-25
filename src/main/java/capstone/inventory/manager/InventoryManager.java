package capstone.inventory.manager;

import capstone.inventory.dto.ProductAvailability;
import capstone.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryManager {

    private final InventoryRepository repository;

    @Autowired
    public InventoryManager(InventoryRepository repository) {
        this.repository = repository;
    }

    public List<ProductAvailability> checkAvailability(List<String> productIds) {
        return repository.checkAvailability(productIds);
    }

}
