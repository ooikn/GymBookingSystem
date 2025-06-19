/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GymBookingSystem;

/**
 *
 * @author ooikn
 */

import java.io.*;
import java.util.*;
public class BookingList {
    private ArrayList <Booking> bookingList = new ArrayList <Booking>();
    /*
    //add grocery shopping at index 1
    bookingList.add(1, "grocery shopping");
    //remove item at index 0
    todoList.remove(0);
    //add watch movie
    todoList.add("watch movie");
    //modify the list by changing watch movie to assignment 2
    todoList.set(2, "assignment 2");for(int i=0; i < bookingList.size(); i++){
        System.out.println(bookingList.get(i));
    }
}*/

    public void addBooking(Booking b){
        bookingList.add(b);
    }
    
    public boolean updateBooking (String bookingId, Booking updatedBooking){
        for (int i = 0; i < bookingList.size(); i++) {
            if (bookingList.get(i).getBookingId().equalsIgnoreCase(bookingId)) {
                bookingList.set(i, updatedBooking);
                return true;
            }
        }
        return false;
    }
    
    public String deleteBooking(String bookingId){
        Booking b = searchBooking(bookingId);
        if(b != null){
            bookingList.remove(b);
            return "Successfully deleted!";
        }
        else{
            return "Booking cannot be found!";
        }
    }
    
    public void displayBooking() {
        for (Booking b : bookingList) {
            System.out.println(b.toString() + "/n");
        }
    }

    public Booking searchBooking(String bookingId){
        for (Booking b : bookingList) {
                if (b.getBookingId().equalsIgnoreCase(bookingId)) {
                    return b;
                }
            }
            return null;
    }

    public void saveBookingToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("bookingList.txt", true))) {
            for (Booking b : bookingList) {
                pw.println(b);
                pw.println(); // extra line for spacing
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
