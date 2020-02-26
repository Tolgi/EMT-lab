package mk.ukim.finki.emt.sports_shop.service.impl;

import mk.ukim.finki.emt.sports_shop.models.Category;
import mk.ukim.finki.emt.sports_shop.repository.PersistentCategoryRepository;
import mk.ukim.finki.emt.sports_shop.service.CategoryService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Profile("persist")
public class PersistentCategoryServiceImpl implements CategoryService {

    private PersistentCategoryRepository repo;

    public PersistentCategoryServiceImpl(PersistentCategoryRepository repo){
        this.repo = repo;
    }

    @Override
    public List<Category> getAll(){
        return repo.getAll();
    }
}
