package mk.ukim.finki.emt.sports_shop.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    /*@OneToMany(mappedBy = "manufacturer",
            fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Product> products;*/

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

  /*  public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> devices) {
        this.products = devices;
    }*/
}
