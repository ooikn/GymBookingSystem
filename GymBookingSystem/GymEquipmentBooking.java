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
public class GymEquipmentBooking extends Booking{
    private final double pricePerHour = 10;
    private double totalPrice;
    
    public GymEquipmentBooking(String memberId, String bookingId, Date date, LocalTime startTime, LocalTime endTime){
        super(memberId, bookingId, date, startTime, endTime);
    }
    
    @Override
    public double calculateTotalPrice(){
        int hours = super.calculateHour();
        totalPrice = pricePerHour * hours;
        if(hours > 5){
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
