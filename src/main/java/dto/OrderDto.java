package dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter

public class OrderDto {

    private String orderId;
    private String date;
    private String customerId;
    private List<OrderDetailsDto> orderDetails;

}
