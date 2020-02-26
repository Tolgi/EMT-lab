package mk.ukim.finki.emt.sports_shop.repository;


import mk.ukim.finki.emt.sports_shop.models.Manufacturer;
import mk.ukim.finki.emt.sports_shop.models.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ManufacturerRepository {

    private List<Manufacturer> manufacturers = null;
    private Long counter;

    @PostConstruct
    public void init(){

        counter = 1l;

        manufacturers = new ArrayList<>();
        Manufacturer m1 = new Manufacturer();
        m1.setId(1l);
        m1.setName("Converse");
        manufacturers.add(m1);

        Manufacturer m2 = new Manufacturer();
        m2.setId(2l);
        m2.setName("Nike");
        manufacturers.add(m2);

        Manufacturer m3 = new Manufacturer();
        m3.setId(3l);
        m3.setName("Adidas");
        manufacturers.add(m3);

    }

    public List<Manufacturer> findAll() { return manufacturers; }

    public Optional<Manufacturer> findById(Long manufacturerId){
        return manufacturers.stream().filter(manufacturer -> manufacturer.getId().equals(manufacturerId)).findAny();
    }


    private Long getNextId() {
        return counter++;
    }

}
