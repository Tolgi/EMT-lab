package mk.ukim.finki.emt.sports_shop.service.impl;

import mk.ukim.finki.emt.sports_shop.models.Category;
import mk.ukim.finki.emt.sports_shop.models.Manufacturer;
import mk.ukim.finki.emt.sports_shop.models.Product;
import mk.ukim.finki.emt.sports_shop.repository.PersistentProductRepository;
import mk.ukim.finki.emt.sports_shop.service.CategoryService;
import mk.ukim.finki.emt.sports_shop.service.ManufacturerService;
import mk.ukim.finki.emt.sports_shop.service.ProductService;
import mk.ukim.finki.emt.sports_shop.web.exceptions.CategoryNotFoundException;
import mk.ukim.finki.emt.sports_shop.web.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.emt.sports_shop.web.exceptions.ProductNotFoundException;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Profile("persist")
public class PersistentProductServiceImpl implements ProductService {

    private PersistentProductRepository repo;
    private ManufacturerService manufacturerService;
    private CategoryService categoryService;

    public PersistentProductServiceImpl(PersistentProductRepository repo, ManufacturerService manufacturerService, CategoryService categoryService){
        this.repo = repo;
        this.manufacturerService = manufacturerService;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> getAll(){
        return repo.getAll();
    }

    @Override
    public Product getById(Long productId) throws ProductNotFoundException {

        Optional<Product> product = repo.getById(productId);
        if(!product.isPresent()){

            throw new ProductNotFoundException();
        }

        return product.get();
    }


    @Override
    public Product addNewProduct(Product newProduct, Long categoryId, Long manufaturerId) throws ManufacturerNotFoundException, CategoryNotFoundException {

        Optional<Category> category = categoryService.getAll().stream().filter(c -> c.getId().equals(categoryId)).findAny();
        if(!category.isPresent()){
            throw new CategoryNotFoundException();
        }
        newProduct.setCategory(category.get());


        Optional<Manufacturer> manufacturer = manufacturerService.getAll().stream().filter(m -> m.getId().equals(manufaturerId)).findAny();
        if(!manufacturer.isPresent()){
            throw new ManufacturerNotFoundException();
        }

        newProduct.setManufacturer(manufacturer.get());

        repo.save(newProduct);
        return newProduct;
    }


    @Override
    public void delete(Long productId){
        Optional<Product> product = repo.getById(productId);
        if (!product.isPresent()) {
            throw new ProductNotFoundException();
        }

        repo.delete(product.get());
    }

    @Override
    public Product update(Product product) throws ProductNotFoundException{
        return null;
    }

    @Override
    public List<Product> getByCategory(Long categoryId) throws ProductNotFoundException {

        Optional<Category> category = categoryService.getAll().stream().filter(c -> c.getId().equals(categoryId)).findAny();
        List<Product> products = repo.getByCategory(category.get());

        return products;
    }

    @Override
    public List<Product> getByCategoryandManufacturer(Long categoryId, Long manufacturerId){
        Optional<Category> category = categoryService.getAll().stream().filter(c -> c.getId().equals(categoryId)).findAny();
        Optional<Manufacturer> manufacturer = manufacturerService.getAll().stream().filter(m -> m.getId().equals(manufacturerId)).findAny();

        List<Product> products = repo.getByCategoryAndManufacturer(category.get(), manufacturer.get());

        return products;

    }

    @Override
    public Double calculatePrice(Long categoryId){
        Optional<Category> category = categoryService.getAll().stream().filter(c -> c.getId().equals(categoryId)).findAny();
        Double price = repo.calculatePrice(category.get());

        return price;
    }

    @Override
    public Page<Product> getByPage(Pageable pageable) {
        return repo.findAll(pageable);
    }


}
