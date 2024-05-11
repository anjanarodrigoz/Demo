package entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class OrderDetails {

    private String order_Id;
    private String item_Code;
    private int qty;
    private double unitPrice;

}
