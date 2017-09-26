package by.it_academy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class OrderDTO {
    private long orderId;
    private Date date;
    private String name;
    private String carName;
    private Date rentalStartDate;
    private int period;
    private double total;
    private String damages;
    private String refusalReason;
    private String status;
}