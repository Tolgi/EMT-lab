package mk.ukim.finki.emt.sports_shop.web.rest;


import mk.ukim.finki.emt.sports_shop.models.Product;
import mk.ukim.finki.emt.sports_shop.service.ProductService;
import mk.ukim.finki.emt.sports_shop.web.exceptions.ProductNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rest/products")
public class ProductRestfulController {

    private ProductService productService;

    public ProductRestfulController(ProductService productService){
        this.productService = productService;
    }


    @GetMapping
    public List<Product> getAllProducts(){

        return productService.getAll();
    }


    @GetMapping(value = "/{id}")
    public Product getById(@PathVariable("id") Long productId){

        return productService.getById(productId);

    }


    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable("categoryId") Long categoryId){

        return productService.getByCategory(categoryId);

    }


    @GetMapping("/category/{categoryId}/manufacturer/{manufacturerId}")
    public List<Product> getProductsByCategoryandManufacturer(@PathVariable("categoryId") Long categoryId, @PathVariable("manufacturerId") Long manufacturerId){

        return productService.getByCategoryandManufacturer(categoryId, manufacturerId);
    }



    @GetMapping("/category/{categoryId}/price")
    public Double getPriceByCategory(@PathVariable("categoryId") Long categoryId){

        return productService.calculatePrice(categoryId);
    }

}
