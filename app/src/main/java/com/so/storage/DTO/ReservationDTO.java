package com.so.storage.DTO;

public class ReservationDTO {
    private String product_type;
    private String booking_start;
    private String booking_end;
    private String location_id;

    public ReservationDTO() {}

    public ReservationDTO(String product_type, String booking_start, String booking_end, String location_id) {
        this.product_type = product_type;
        this.booking_start = booking_start;
        this.booking_end = booking_end;
        this.location_id = location_id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getBooking_start() {
        return booking_start;
    }

    public void setBooking_start(String booking_start) {
        this.booking_start = booking_start;
    }

    public String getBooking_end() {
        return booking_end;
    }

    public void setBooking_end(String booking_end) {
        this.booking_end = booking_end;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }
}
