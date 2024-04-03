package dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CustomerDto {

    private String id;
    private String name;
    private String mobileNumber;
    private String emailAddress;

}
