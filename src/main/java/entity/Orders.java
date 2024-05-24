package entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Orders {

    @Id
    @Column(nullable = false)
    private String id;
    private String date;

    @ManyToOne()
    @JoinColumn(name = "customer_Id",nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "orders")
    private List<OrderDetails> ordersDetailList = new ArrayList<>();

    public Orders(String id, String date) {
        this.id = id;
        this.date = date;
    }
}
