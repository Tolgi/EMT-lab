package mk.ukim.finki.emt.sports_shop.repository;

import mk.ukim.finki.emt.sports_shop.models.Category;
import mk.ukim.finki.emt.sports_shop.models.Manufacturer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PersistentManufacturerRepository extends Repository<Manufacturer, Long> {

    @Query("select m from Manufacturer m")
    List<Manufacturer> getAll();

    Manufacturer save(Manufacturer m);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="delete from Manufacturer m where m=:m")
    void delete(@Param("m") Manufacturer m);

    @Query("select m from Manufacturer m where m.id=:id")
    Optional<Manufacturer> getById(@Param("id") Long id);
}
