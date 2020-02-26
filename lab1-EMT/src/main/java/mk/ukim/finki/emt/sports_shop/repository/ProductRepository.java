package mk.ukim.finki.emt.sports_shop.repository;


import mk.ukim.finki.emt.sports_shop.models.Category;
import mk.ukim.finki.emt.sports_shop.models.Manufacturer;
import mk.ukim.finki.emt.sports_shop.models.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private List<Product> products = null;
    private ManufacturerRepository manufacturerRepository = null;
    private CategoryRepository categoryRepository = null;
    private Long counter;

    public ProductRepository(ManufacturerRepository manufacturerRepository, CategoryRepository categoryRepository){
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
    }


    @PostConstruct
    public void init(){

        counter = 1l;

        products = new ArrayList<>();
        Product p1 = new Product();
        p1.setId(getNextId());
        p1.setManufacturer(manufacturerRepository.findById(1l).get());
        p1.setCategory(categoryRepository.findById(1l).get());
        p1.setDescription("As soon as you put them on and start doing your thing, their true life begins. You define them. You determine their journey.");
        p1.setImgLink("http://shop.arthurs.com.mk/image/cache/data/CONVERSE/2019/Chuck%2070/563472C_standard-386x386.jpg");
        p1.setName("Chuck 70 Love Graphic");
        products.add(p1);

        Product p2 = new Product();
        p2.setId(getNextId());
        p2.setManufacturer(manufacturerRepository.findById(2l).get());
        p2.setCategory(categoryRepository.findById(2l).get());
        p2.setDescription("Nike produces a wide range of sports equipment. Their first products were track running shoes. They currently also make shoes, jerseys, shorts, cleats");
        p2.setImgLink("https://www.sportvision.mk/files/images/slike_proizvoda/AO0053-010.jpg");
        p2.setName("NK DRY ACDMY TRK SUIT");
        products.add(p2);


        Product p3 = new Product();
        p3.setId(getNextId());
        p3.setManufacturer(manufacturerRepository.findById(3l).get());
        p3.setCategory(categoryRepository.findById(1l).get());
        p3.setDescription("Adidas is the largest sportswear manufacturer in Europe, and the second largest in the world, after Nike.");
        p3.setImgLink("https://www.kosmosstore.com/4452-large_default/adidas-pod-s-3-1-core-black-core-black-footwear-white-db3378.jpg");
        p3.setName("SOLAR GLIDE ST");
        products.add(p3);


    }

    public List<Product> findAllProducts() { return products; }


    public Product save(Product newProduct){
        newProduct.setId(getNextId());
        products.add(newProduct);
        return newProduct;
    }

    public void delete(Long productId){

        products.removeIf(v -> {
            return v.getId().equals(productId);
        });

    }

    public Optional<Product> findById(Long productId){
        return products.stream().filter(product -> product.getId().equals(productId)).findAny();
    }



    private Long getNextId() {
        return counter++;
    }


}
