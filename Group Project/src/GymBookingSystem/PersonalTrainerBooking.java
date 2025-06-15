/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GymBookingSystem;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author ooikn
 */
public class PersonalTrainerBooking extends Booking{
    private final double pricePerHour = 10;
    private double totalPrice;
    private final double trainerPricePerHour = 50;
    
    public PersonalTrainerBooking(String bookingId, String memberId, LocalDate date, LocalTime startTime, LocalTime endTime){
        super(bookingId, memberId, date, startTime, endTime);
    }
    
    @Override
    public double calculateTotalPrice(){
        int hours = super.calculateHour();
        totalPrice = (pricePerHour + trainerPricePerHour) * hours;
        if(hours > 5){
            return totalPrice = totalPrice * (totalPrice * 0.10);
        }
        else{
            return totalPrice;
        }
    }
    
    @Override
    public String toString(){
        return "Booking ID: " + super.getBookingId() + "\nMember ID: " + super.getMemberId() + "\nDate: " + super.getDate() + "\nTime: " + super.getStartTime() + " - " + super.getEndTime() + "Total Price: " + calculateTotalPrice();
    }
}
