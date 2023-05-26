package pl.it.camp.sklep.db;

import pl.it.camp.sklep.model.product.Product;

import java.util.List;

public interface IProductRepository {
    void addProduct(Product product);

    boolean supplyProduct(String code, int quantity);

    boolean buyProduct(String code, int quantity);

    Product findProductByCode(String code);

    List<Product> getProducts();
}
