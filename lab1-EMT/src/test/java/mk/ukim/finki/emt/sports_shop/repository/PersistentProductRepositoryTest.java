package mk.ukim.finki.emt.sports_shop.repository;

import mk.ukim.finki.emt.sports_shop.models.Category;
import mk.ukim.finki.emt.sports_shop.models.Manufacturer;
import mk.ukim.finki.emt.sports_shop.models.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PersistentProductRepositoryTest {

    @Autowired
    private PersistentProductRepository repo;


    @Before
    public void init(){

        Product p1 = new Product();
        p1.setName("Chuck");
        repo.save(p1);

        Product p2 = new Product();
        p2.setName("NK DRY");
        repo.save(p2);
    }

    @Test
    public void getAll(){
        List<Product> productList = repo.getAll();
        Assert.assertEquals(2,productList.size());

    }

    @Test
    public void getById(){

        Optional<Product> product = repo.getById(1L);
        Assert.assertNotNull(product.get());
        Assert.assertEquals("Chuck",product.get().getName());

    }


}