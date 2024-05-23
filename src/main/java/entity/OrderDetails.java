package entity;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class OrderDetails {

    @EmbeddedId
    private OrderDetailKey id;
    private int qty;


    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id",nullable = false)
    Item item;


    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id",nullable = false)
    Orders orders;

}
