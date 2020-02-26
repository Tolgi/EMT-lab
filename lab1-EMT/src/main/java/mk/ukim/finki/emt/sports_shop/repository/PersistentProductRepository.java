package mk.ukim.finki.emt.sports_shop.repository;

import mk.ukim.finki.emt.sports_shop.models.Category;
import mk.ukim.finki.emt.sports_shop.models.Manufacturer;
import mk.ukim.finki.emt.sports_shop.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PersistentProductRepository extends Repository<Product,Long>, PagingAndSortingRepository<Product, Long> {

    @Query("select p from Product p")
    List<Product> getAll();

    Product save(Product p);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="delete from Product p where p=:p")
    void delete(@Param("p") Product p);

    @Query("select p from Product p where p.id=:id")
    Optional<Product> getById(@Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="update Product p set p.name=:name where p.id=:id")
    Product updateByName(@Param("id") Long id,@Param("name") String name);

    @Query("select p from Product p where p.category=:category")
    List<Product> getByCategory(@Param("category") Category category);

    @Query("select p from Product p where p.category=:category and p.manufacturer=:manufacturer")
    List<Product> getByCategoryAndManufacturer(@Param("category") Category category, @Param("manufacturer") Manufacturer manufacturer);

    @Query(value="select sum(p.price) from Product p where p.category=:category")
    Double calculatePrice(@Param("category") Category category);

    Page<Product> findAll(Pageable pageable);

}
