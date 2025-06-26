/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GymBookingSystem;

/**
 *
 * @author ooikn
 */

// import to use the date and time
import java.time.LocalTime;
import java.util.Date;

public class GymEquipmentBooking extends Booking{
    private final double pricePerHour = 10;
    private double totalPrice;
    
    public GymEquipmentBooking(String memberId, String bookingId, Date date, LocalTime startTime, LocalTime endTime){
        //initialize the contructor of superclass 
        super(memberId, bookingId, date, startTime, endTime);
    }
    
    @Override
    public double calculateTotalPrice(){
        // get the hours from its superclass 
        int hours = super.calculateHour();
        
        // calculate total price based on hours
        totalPrice = pricePerHour * hours;
        
        // give discount if book more than 3 hours in one booking
        if(hours > 3){
            return totalPrice = totalPrice * (totalPrice * 0.05);
        }
        else{
            return totalPrice;
        }
    }
    
    @Override
    public String toString(){
        return "Booking ID: " + super.getBookingId() + "\nDate: " + super.getDate() + "\nTime: " + super.getStartTime() + " - " + super.getEndTime();
    }
}
