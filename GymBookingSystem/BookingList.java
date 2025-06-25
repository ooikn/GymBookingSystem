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
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookingList {
    private ArrayList <Booking> bookingList;

    public BookingList() {
        bookingList = new ArrayList<>();
        loadFromFile();
    }
    
    public void addBooking(Booking addBooking){
        bookingList.add(addBooking); // add booking details to the bookingList array list
        addToFile(); // add booking to file from the bookingList array list
    }
    
    public void updateBooking (Booking updateBooking){
        boolean found = false;
        
        for (int i = 0; i < bookingList.size(); i++) {
            if (bookingList.get(i).getBookingId().equals(updateBooking.getBookingId())) {
                bookingList.set(i, updateBooking);
                
                // stop looping once updated
                found = true;
                break;
            }
        }
        
        saveToFile();
    }
    
    public Booking searchBooking(String bookingId) {
        for (Booking b : bookingList) {
            if (b.getBookingId().equalsIgnoreCase(bookingId)) {
                return b;  
            }
        }
        return null;
    }
    
    public boolean deleteBooking(String bookingId){
        boolean found = false;
        
        Booking b = searchBooking(bookingId);
        for(Booking booking:bookingList){
            if(booking.getBookingId().equals(b.getBookingId())){
                bookingList.remove(b);
                saveToFile();
                found = true;
                break;
            }
        }
        
        return found;
    }
    
    public ArrayList<Booking> getBookingList() {
        return new ArrayList<>(bookingList); // Return a copy for safety
    }
    
    public void addToFile(){
        File outFile = new File("bookings.txt"); 
        FileWriter outFileStream = null;
        PrintWriter outStream = null;    
        
        try  {
            outFileStream = new FileWriter(outFile, true); //append the booking details in file
            outStream = new PrintWriter(outFileStream);
            for (Booking booking : bookingList) {
                // set the date and time format
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
                
                // convert to string to store human readable format in file
                String dateStr = dateFormat.format(booking.getDate());
                String startTimeStr = booking.getStartTime().format(timeFormat);
                String endTimeStr = booking.getEndTime().format(timeFormat);
                
                // Format: memberId,bookingId,date,startTime,endTime
                outStream.println(booking.getMemberId() + "," + booking.getBookingId() + "," + dateStr + "," + startTimeStr + "," + endTimeStr);
            }
        } catch (IOException e) {
            System.out.println("Error saving booking data: " + e.getMessage());
        }finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (Exception e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }
    
    public void saveToFile(){
        File outFile = new File("bookings.txt");
        FileWriter outFileStream = null;
        PrintWriter outStream = null;    
        
        try  {
            outFileStream = new FileWriter(outFile, false);
            outStream = new PrintWriter(outFileStream);
            for (Booking booking : bookingList) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
                
                // convert to string to store in file
                String dateStr = dateFormat.format(booking.getDate());
                String startTimeStr = booking.getStartTime().format(timeFormat);
                String endTimeStr = booking.getEndTime().format(timeFormat);
                // Format: memberId,bookingId,date,startTime,endTime
                outStream.println(booking.getMemberId() + "," + booking.getBookingId() + "," + dateStr + "," + startTimeStr + "," + endTimeStr);
            }
        } catch (IOException e) {
            System.out.println("Error saving booking data: " + e.getMessage());
        }finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (Exception e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }
    
    private void loadFromFile() {
        bookingList.clear();
        File inFile = new File("bookings.txt");
        if (!inFile.exists()) {
            try {
                inFile.createNewFile(); //create file if not exist
            } catch (IOException e) {
                System.err.println("Error creating bookings file: " + e.getMessage());
            }
            //return;
        }

        try (BufferedReader inStream = new BufferedReader(new FileReader(inFile))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
            String str;
            while ((str = inStream.readLine()) != null) {
                String[] parts = str.split(",");
                if (parts.length == 5) { 
                    try {
                        String memberId = parts[0].trim();
                        String bookingId = parts[1].trim();
                        
                        // convert it to original format
                        Date date = dateFormat.parse(parts[2].trim()); 
                        LocalTime startTime = LocalTime.parse(parts[3].trim(), timeFormat); 
                        LocalTime endTime = LocalTime.parse(parts[4].trim(), timeFormat); 

                        bookingList.add(new GymEquipmentBooking(memberId, bookingId, date, startTime, endTime));
                    } catch (Exception e) { 
                        System.err.println("Error creating GymEquipmentBooking for line: " + str + ". Error: " + e.getMessage());
                    }
                    System.err.println("Skipping malformed line in members.data: " + str);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading member data on load: " + e.getMessage());
        }
    }
    
    public String getBookingId(String bookingId){
        try (BufferedReader inStream = new BufferedReader(new FileReader("bookings.txt"))) {
            String str;
            while ((str = inStream.readLine()) != null) {
                String[] parts = str.split(",");
                if (parts.length >= 5 && parts[1].trim().equals(bookingId.trim())) {
                    return parts[1].trim();
                }
            }
        } catch (IOException e) {
            System.err.println("Error finding booking ID: " + e.getMessage());
        }
        return null;
    }
}
