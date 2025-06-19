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
    public PersonalTrainerBooking(String bookingId, String memberId, LocalDate date, LocalTime startTime, LocalTime endTime){
        super(bookingId, memberId, date, startTime, endTime);
    }
    
    @Override
    public double calculateTotalPrice(){
        int hours = super.calculateHour();
        double trainerRate;
        if (hours <= 2) {
            trainerRate = 30;
        } else if (hours <= 4) {
            trainerRate = 28;
        } else {
            trainerRate = 25;
        }

        double totalPrice = (trainerRate + 10.0) * hours; // trainer + gym usage
        return totalPrice;
    }
    
    @Override
    public String toString(){
        return "Booking ID: " + super.getBookingId() + "\nMember ID: " + super.getMemberId() + "\nDate: " + super.getDate() + "\nTime: " + super.getStartTime() + " - " + super.getEndTime() + "Total Price: " +  String.format("%.2f", calculateTotalPrice());
    }
}
