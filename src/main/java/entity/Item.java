package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Item {

    private String id;
    private String name;
    private int qty;
    private double price;
}
