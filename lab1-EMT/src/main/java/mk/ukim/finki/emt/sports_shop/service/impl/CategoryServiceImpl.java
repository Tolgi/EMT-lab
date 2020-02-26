package mk.ukim.finki.emt.sports_shop.service.impl;

import mk.ukim.finki.emt.sports_shop.models.Category;
import mk.ukim.finki.emt.sports_shop.repository.CategoryRepository;
import mk.ukim.finki.emt.sports_shop.service.CategoryService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.security.PublicKey;
import java.util.List;


@Repository
@Profile("in-memory")
public class CategoryServiceImpl implements CategoryService {

    public CategoryRepository repo;
    public CategoryServiceImpl(CategoryRepository repo){
        this.repo = repo;
    }

    @Override
    public List<Category>getAll(){
        return repo.findAll();
    }
}
