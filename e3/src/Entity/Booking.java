package Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Booking {
    private int id;
    private String roomId;
    private String customerId;
    private LocalDateTime check_In_datetime;
    private LocalDateTime check_Out_datetime;
    public Booking() {;}

    public Booking(int id, Room room, Customer customer, LocalDateTime check_In_datetime, LocalDateTime check_Out_datetime) {
        this.id = id;
        this.roomId = room.getId();
        this.customerId = customer.getId();
        this.check_In_datetime = check_In_datetime;
        this.check_Out_datetime = check_Out_datetime;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getCheck_In_datetime() {
        return check_In_datetime;
    }

    public void setCheck_In_datetime(LocalDateTime check_In_datetime) {
        this.check_In_datetime = check_In_datetime;
    }

    public LocalDateTime getCheck_Out_datetime() {
        return check_Out_datetime;
    }

    public void setCheck_Out_datetime(LocalDateTime check_Out_datetime) {
        this.check_Out_datetime = check_Out_datetime;
    }


    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", roomId='" + roomId + '\'' +
                ", customerId=" + customerId +
                ", check_In_datetime=" + check_In_datetime +
                ", check_Out_datetime=" + check_Out_datetime +
                '}';
    }



}
