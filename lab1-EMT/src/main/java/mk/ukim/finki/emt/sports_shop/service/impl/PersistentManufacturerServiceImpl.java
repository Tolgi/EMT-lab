package mk.ukim.finki.emt.sports_shop.service.impl;

import mk.ukim.finki.emt.sports_shop.models.Manufacturer;
import mk.ukim.finki.emt.sports_shop.repository.PersistentManufacturerRepository;
import mk.ukim.finki.emt.sports_shop.service.ManufacturerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Profile("persist")
public class PersistentManufacturerServiceImpl implements ManufacturerService {

    private PersistentManufacturerRepository repo;

    public PersistentManufacturerServiceImpl(PersistentManufacturerRepository repo){
        this.repo = repo;
    }

    @Override
    public List<Manufacturer> getAll() {
        return repo.getAll();
    }

}
