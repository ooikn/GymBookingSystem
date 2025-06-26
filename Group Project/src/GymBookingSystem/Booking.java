/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GymBookingSystem;

/**
 *
 * @author ooikn
 */
import java.time.LocalTime;
import java.util.Date;

public abstract class Booking {
    // encapsulation
    private String memberId;
    private String bookingId;
    private Date date;
    private LocalTime startTime;
    private LocalTime endTime;
    
    public Booking (String memberId, String bookingId, Date date, LocalTime startTime, LocalTime endTime){
        this.bookingId = bookingId;
        this.memberId = memberId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public String getMemberId(){
        return memberId;
    }
    
    public String getBookingId(){
        return bookingId;
    }
    
    public Date getDate(){
        return date;
    }
    
    public LocalTime getStartTime(){
        return startTime;
    }
    
    public LocalTime getEndTime(){
        return endTime;
    }
    
    // calculate total hours booked
    public int calculateHour(){
        int st = startTime.getHour();
        int et = endTime.getHour();
        int totalHours = et - st;
        return totalHours;
    }
    
    // abstract methods - will be implemented in the GymEquipmentBooking class
    public abstract double calculateTotalPrice();
    public abstract String toString();
}
