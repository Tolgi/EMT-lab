package mk.ukim.finki.emt.sports_shop.web.controllers;

import mk.ukim.finki.emt.sports_shop.dto.ChargeRequest;
import mk.ukim.finki.emt.sports_shop.models.Category;
import mk.ukim.finki.emt.sports_shop.models.Manufacturer;
import mk.ukim.finki.emt.sports_shop.models.Product;
import mk.ukim.finki.emt.sports_shop.service.CategoryService;
import mk.ukim.finki.emt.sports_shop.service.ManufacturerService;
import mk.ukim.finki.emt.sports_shop.service.ProductService;
import mk.ukim.finki.emt.sports_shop.web.exceptions.CategoryNotFoundException;
import mk.ukim.finki.emt.sports_shop.web.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.emt.sports_shop.web.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {

    public ProductService productService;
    public ManufacturerService manufacturerService;
    public CategoryService categoryService;

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;


    public ProductController(ProductService productService, ManufacturerService manufacturerService, CategoryService categoryService){
        this.productService = productService;
        this.manufacturerService = manufacturerService;
        this.categoryService = categoryService;
    }


    @GetMapping("products")
    public String products(Model model){

        model.addAttribute("productList", productService.getAll());
        return "products";
    }

    @GetMapping("products/{id}")
    public String productDetails(@PathVariable("id") Long productId, Model model){

        Product product = productService.getById(productId);
        model.addAttribute("productDetails", product);
        model.addAttribute("amount", product.getPrice().intValue()); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "productDetails";

    }

    @GetMapping("products/add")
    public String addProductForm(Model model){

        model.addAttribute("categoryList", categoryService.getAll());
        model.addAttribute("manufacturerList", manufacturerService.getAll());
        model.addAttribute("product", new Product());

        return "addProduct";
    }


    @ExceptionHandler({CategoryNotFoundException.class})
    @PostMapping("products/add")
    public String addProduct(@ModelAttribute Product newProduct, Model model){
        productService.addNewProduct(newProduct, newProduct.getCategory().getId(),
                newProduct.getManufacturer().getId());

        return "redirect:/products";

    }

    @DeleteMapping("products")
    public String deleteDevice(HttpServletRequest request) {
        Long productId = Long.parseLong(request.getParameter("productId"));
        productService.delete(productId);
        return "redirect:/products";
    }



    @GetMapping("/products/page/{page}/size/{size}")
    public String getByPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size, Model model){

        model.addAttribute("productList", productService.getByPage(PageRequest.of(page, size))) ;
        return "products";


    }





}
