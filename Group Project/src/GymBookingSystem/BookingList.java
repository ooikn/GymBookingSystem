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
    // declare an array list name bookingList
    private ArrayList <Booking> bookingList;

    public BookingList() {
        bookingList = new ArrayList<>();
        loadFromFile(); // load existing bookings from file on initialization
    }
    
    public void addBooking(Booking addBooking){
        bookingList.add(addBooking); // add new booking into array list
        saveToFile(); // save the entire list to the file
    }
    
    public void updateBooking (Booking updateBooking){
        for (int i = 0; i < bookingList.size(); i++) {
            // find the booking to be replaced by comparing bookingId
            if (bookingList.get(i).getBookingId().equals(updateBooking.getBookingId())) {
                bookingList.set(i, updateBooking); // replace old booking details with new booking details
                saveToFile();
                break; // stop looping once updated
            }
        } 
    }
    
    public Booking searchBooking(String bookingId) {
        for (Booking b : bookingList) {
            if (b.getBookingId().equalsIgnoreCase(bookingId)) {
                return b;  // return booking if found
            }
        }
        return null; // return null if booking not found
    }
    
    public boolean deleteBooking(String bookingId){
        Booking b = searchBooking(bookingId);
        if(b != null){
            bookingList.remove(b);
            saveToFile();
            return true;
        }
        return false;
    }
    
    // get every booking in the array list
    public ArrayList<Booking> getBookingList() {
        return new ArrayList<>(bookingList); 
    }
    
    public void saveToFile(){
        File outFile = new File("bookings.txt");
        FileWriter outFileStream = null;
        PrintWriter outStream = null;    
        
        try  {
            outFileStream = new FileWriter(outFile, false); // overwrite the file
            outStream = new PrintWriter(outFileStream);
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
                
            for (Booking booking : bookingList) {

                // convert date and time to string to store in file
                String dateStr = dateFormat.format(booking.getDate());
                String startTimeStr = booking.getStartTime().format(timeFormat);
                String endTimeStr = booking.getEndTime().format(timeFormat);
                
                // Format: memberId,bookingId,date,startTime,endTime
                outStream.println(booking.getMemberId() + "," + booking.getBookingId() + "," + dateStr + "," + startTimeStr + "," + endTimeStr);
            }
        } catch (IOException e) {
            System.out.println("Error saving booking data: " + e.getMessage());
        }
    }
    
    private void loadFromFile() {
        //bookingList.clear(); //clear existing booking to prevent duplicate on reload
        
        File inFile = new File("bookings.txt");
        
        //create file if not exist
        if (!inFile.exists()) {
            try {
                inFile.createNewFile(); 
                System.out.println("New bookings.txt file created!");
            } catch (IOException e) {
                System.err.println("Error creating bookings.txt file: " + e.getMessage());
            }
            return; // exit the method, nothing to read yet
        }

        try  {
            FileReader inFileReader = new FileReader(inFile);
            BufferedReader inStream = new BufferedReader(inFileReader);

            // formatting the date (day-month-year) and time(hour:minute)
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
            String str;
            
            // read every line in the file to add to the booking list
            while ((str = inStream.readLine()) != null) {
                String[] parts = str.split(",");
                
                try {
                    String memberId = parts[0].trim();
                    String bookingId = parts[1].trim();

                    // Parse date and time back to date and localtime object
                    Date date = dateFormat.parse(parts[2]); 
                    LocalTime startTime = LocalTime.parse(parts[3], timeFormat); 
                    LocalTime endTime = LocalTime.parse(parts[4], timeFormat); 
                    
                    // load every bookings line by line into the array list
                    bookingList.add(new GymEquipmentBooking(memberId, bookingId, date, startTime, endTime));
                } catch (Exception e) { 
                    System.out.println("Error creating booking object for line: " + str + ". Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading member data on load: " + e.getMessage());
        }
    }
}
