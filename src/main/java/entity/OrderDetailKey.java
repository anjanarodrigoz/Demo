package entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@Embeddable
public class OrderDetailKey implements Serializable {

    @Column(name = "item_id",nullable = false)
    private String itemId;

    @Column(name = "order_id",nullable = false)
    private String orderId;

    @Column(name = "unit_price",nullable = false)
    private double unitPrice;

    
}
