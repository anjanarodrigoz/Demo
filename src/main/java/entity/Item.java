package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Item {

    @Id
    private String id;
    private String name;
    private int qty;
    private double price;


    @OneToMany(mappedBy = "item")
    List<OrderDetails> orderDetailsList = new ArrayList<>();

    public Item(String id, String name, int qty, double price) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
    }
}
