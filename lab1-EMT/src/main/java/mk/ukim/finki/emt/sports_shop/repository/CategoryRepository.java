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
public class CategoryRepository {


    private List<Category> categories = null;
    private Long counter;



    @PostConstruct
    public void init(){

        counter = 1l;

        categories = new ArrayList<>();
        Category c1 = new Category();
        c1.setId(1l);
        c1.setName("Sneakers");
        categories.add(c1);

        Category c2 = new Category();
        c2.setId(2l);
        c2.setName("Тracksuits");
        categories.add(c2);

        Category c3 = new Category();
        c3.setId(3l);
        c3.setName("Jackets");
        categories.add(c3);
        
    }

    public List<Category> findAll() { return categories; }

    public Optional<Category> findById(Long categoryId){
        return categories.stream().filter(c -> c.getId().equals(categoryId)).findAny();
    }


    private Long getNextId() {
        return counter++;
    }

}
