package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Embeddable
public class OrderDetailKey implements Serializable {

    @Column(name = "item_id",nullable = false)
    private String itemId;

    @Column(name = "order_id",nullable = false)
    private String orderId;

    @Column(name = "unit_price",nullable = false)
    private double unitPrice;

    
}
