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
    protected String memberId;
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
    
    public int calculateHour(){
        int startHour = startTime.getHour();
        int endHour = endTime.getHour();
        int totalHours = endHour - startHour;
        return totalHours;
    }
  
    public abstract double calculateTotalPrice();
    public abstract String toString();
}
