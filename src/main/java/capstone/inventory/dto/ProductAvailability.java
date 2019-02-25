package capstone.inventory.dto;

public class ProductAvailability {

    private final String productId;
    private final int available;

    public ProductAvailability(String productId, int available) {
        this.productId = productId;
        this.available = available;
    }

    public String getProductId() {
        return productId;
    }

    public int getAvailable() {
        return available;
    }
}
