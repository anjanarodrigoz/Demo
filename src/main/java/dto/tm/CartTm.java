package dto.tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class CartTm {

    private String itemId;
    private String ItemName;
    private double unitPrice;
    private int qty;
    private double amount;
    private JFXButton deleteButton;



}


