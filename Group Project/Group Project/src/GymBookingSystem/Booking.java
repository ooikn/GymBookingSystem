/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GymBookingSystem;

/**
 *
 * @author ooikn
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
public abstract class Booking {
    private String bookingId;
    protected String memberId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    
    public Booking (String bookingId, String memberId, LocalDate date, LocalTime startTime, LocalTime endTime){
        this.bookingId = bookingId;
        this.memberId = memberId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public String getBookingId(){
        Random rand = new Random();
        int randomId = rand.nextInt(9000) + 1; //randomly generate a number between 1 to 9000
        return "B" + randomId;
    }
    
    public String getMemberId(){
        return memberId;
    }
    
    public LocalDate getDate(){
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
