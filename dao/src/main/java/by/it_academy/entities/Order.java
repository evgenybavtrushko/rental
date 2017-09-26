package by.it_academy.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class Order {
    private long orderId;
    private long userId;
    private long carId;
    private double total;
    private Date date;
    private Date rentalStartDate;
    private int period;
    private String refusalReason;
    private String damages;
    private OrderStatus status;

    public String getStatus() {
        switch (status) {
            case NEW: {
                return "NEW";
            }
            case  APPROVED_BY: {
               return "APPROVED_BY";
            }
            case DENIED: {
            return "DENIED";
        }
            case ARCHIVE: {
                return "ARCHIVE";
            }
            default:
                return null;
        }
    }
    public void setStatus(String s) {

        switch (s.toUpperCase()) {
            case "NEW": {
                this.status = OrderStatus.NEW;
                return;
            }
            case "APPROVED_BY": {
                this.status = OrderStatus.APPROVED_BY;
                return;

            }case "DENIED": {
                this.status = OrderStatus.DENIED;
                return;
            }
            case "ARCHIVE": {
                this.status = OrderStatus.ARCHIVE;
            }
                default:
                    return;

        }
    }

    public Order(long userId, long carId, double total, Date rentalStartDate, int period) {
        this.userId = userId;
        this.carId = carId;
        this.total = total;
        this.date = (new Date(System.currentTimeMillis()));
        this.rentalStartDate = rentalStartDate;
        this.period = period;
        this.status = OrderStatus.NEW;
    }
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", carId=" + carId +
                ", total=" + total +
                ", date=" + date +
                ", rentalStartDate=" + rentalStartDate +
                ", period=" + period +
                ", refusalReason='" + refusalReason + '\'' +
                ", damages='" + damages + '\'' +
                ", status=" + status +
                '}';
    }
}
