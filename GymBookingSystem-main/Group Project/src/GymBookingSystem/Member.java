/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GymBookingSystem;

/**
 *
 * @author ooikn
 */

public class Member {
    private String memberId;
    private String username;
    private String phoneNo;
    private String email;
    private String password;
    
    public Member(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public Member(String memberId, String username, String phoneNo, String email, String password){
        this.memberId = memberId;
        this.username = username;
        this.phoneNo = phoneNo;
        this.email = email;
        this.password = password;
    }
    
    public String getMemberId(){
        return memberId;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPhoneNo(){
        return phoneNo;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getPassword(){
        return password;
    }
}
