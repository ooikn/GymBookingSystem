/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GymBookingSystem;

/**
 *
 * @author ooikn
 */
import java.util.Random;
public class Member {
    private String memberId;
    private String memberName;
    private String phoneNo;
    private String email;
    //private String password;
    
    public Member(String memberId, String memberName, String phoneNo, String email /*, String password*/){
        memberId = setBookingId();
        this.memberName = memberName;
        this.phoneNo = phoneNo;
        this.email = email;
        //this.password = password;
    }
    
    public String setBookingId(){
        Random rand = new Random();
        int randomId = rand.nextInt(9000) + 1; //randomly generate a number between 1 to 9000
        return "M" + randomId;
    }
    
    public String getBookingId(){
        return memberId;
    }
    
    public String getUsername(){
        return memberName;
    }
    
    public String getPhoneNo(){
        return phoneNo;
    }
    
    public String getEmail(){
        return email;
    }
}
