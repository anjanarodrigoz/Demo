package entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Orders {

    private String id;
    private String date;
    private String customer_id;
}