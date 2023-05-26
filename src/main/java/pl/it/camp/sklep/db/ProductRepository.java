package pl.it.camp.sklep.db;

import org.springframework.stereotype.Component;
import pl.it.camp.sklep.model.product.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository implements IProductRepository {

    private final List<Product> products = new ArrayList<>();

    @Override
    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public boolean supplyProduct(String code, int quantity) {
        Product product = findProductByCode(code);
        if (product != null && quantity > 0) {
            product.setQuantity(product.getQuantity() + quantity);
            return true;
        }
        return false;
    }

    @Override
    public boolean buyProduct(String code, int quantity) {
        Product product = findProductByCode(code);
        if (product != null && product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
            return true;
        }
        return false;
    }

    @Override
    public Product findProductByCode(String code) {
        for (Product element : products) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }
}
