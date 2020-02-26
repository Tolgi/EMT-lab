package mk.ukim.finki.emt.sports_shop.service;

import mk.ukim.finki.emt.sports_shop.models.Manufacturer;
import mk.ukim.finki.emt.sports_shop.models.Product;
import mk.ukim.finki.emt.sports_shop.web.exceptions.CategoryNotFoundException;
import mk.ukim.finki.emt.sports_shop.web.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.emt.sports_shop.web.exceptions.ProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Product addNewProduct(Product newProduct, Long categoryId, Long manufaturerId) throws ManufacturerNotFoundException, CategoryNotFoundException;
    List<Product> getAll();
    Product update(Product product) throws ProductNotFoundException;
    void delete(Long productId);
    Product getById(Long productId) throws ProductNotFoundException;
    List<Product>getByCategory(Long categoryId);
    List<Product>getByCategoryandManufacturer(Long categoryId, Long manufacturerId);
    Double calculatePrice(Long categoryId);
    Page<Product> getByPage(Pageable pageable);
}
