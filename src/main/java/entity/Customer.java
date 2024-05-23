package entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Customer {

    @Id
    private String id;
    private String name;
    private String mobileNumber;
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<Orders> orderList = new ArrayList<>();


    public Customer(String id, String name, String mobileNumber, String email) {
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }
}
