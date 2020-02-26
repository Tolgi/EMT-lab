package mk.ukim.finki.emt.sports_shop.repository;

import mk.ukim.finki.emt.sports_shop.models.Category;
import mk.ukim.finki.emt.sports_shop.models.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PersistentCategoryRepository extends Repository<Category, Long> {

    @Query("select c from Category c")
    List<Category> getAll();

    Category save(Category c);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="delete from Category c where c=:c")
    void delete(@Param("c") Category c);

    @Query("select c from Category c where c.id=:id")
    Optional<Category> getById(@Param("id") Long id);


}
