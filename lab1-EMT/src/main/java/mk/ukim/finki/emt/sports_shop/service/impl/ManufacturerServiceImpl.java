package mk.ukim.finki.emt.sports_shop.service.impl;

import mk.ukim.finki.emt.sports_shop.models.Manufacturer;
import mk.ukim.finki.emt.sports_shop.repository.ManufacturerRepository;
import mk.ukim.finki.emt.sports_shop.service.ManufacturerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("in-memory")
public class ManufacturerServiceImpl implements ManufacturerService {

    public ManufacturerRepository repo;

    public ManufacturerServiceImpl(ManufacturerRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Manufacturer> getAll() {
        return repo.findAll();
    }


}
