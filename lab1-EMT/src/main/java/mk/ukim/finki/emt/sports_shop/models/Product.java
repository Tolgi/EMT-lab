package mk.ukim.finki.emt.sports_shop.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String imgLink;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "man_id")
    private Manufacturer manufacturer;


    @ManyToMany
    @JoinTable(name = "product_accessories",
                joinColumns = @JoinColumn(name = "product_id"),
                inverseJoinColumns = @JoinColumn(name = "accessory_id"))
    private List<Accessory> accessories;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<Accessory> accessories) {
        this.accessories = accessories;
    }

    public void print() {
        System.out.println(category.getName());
        System.out.println(manufacturer.getName());
        System.out.println(id+"  "+name+"  "+price);
        System.out.println(description);
        System.out.println(imgLink);
    }

    public Double getPrice(){
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

