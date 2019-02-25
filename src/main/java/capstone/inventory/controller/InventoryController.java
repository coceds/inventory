package capstone.inventory.controller;

import capstone.inventory.dto.ProductAvailability;
import capstone.inventory.manager.InventoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
public class InventoryController {

    private final InventoryManager manger;

    @Autowired
    public InventoryController(InventoryManager manger) {
        this.manger = manger;
    }

    @RequestMapping(value = "/availability", method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public List<ProductAvailability> checkByProductIds(
            @RequestBody @NotEmpty List<String> productIds
    ) {
        return manger.checkAvailability(productIds);
    }

}