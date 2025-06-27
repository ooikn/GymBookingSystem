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
import java.util.ArrayList;

public class MemberList {
    private ArrayList<Member> memberList;
    
    public MemberList() {
        memberList = new ArrayList<>();
        loadFromFile(); // load existing members from file on initialization
    }
    
    public void addMember(Member newMember) {
        memberList.add(newMember); // add new member into array list
        saveToFile(); // save the entire list to the file
    }
    
    public void updateMemberInfo(Member updateMember){
        for(int i = 0; i < memberList.size(); i++) {
            if(memberList.get(i).getMemberId().equals(updateMember.getMemberId())) {
                memberList.set(i, updateMember);
                saveToFile(); // save the entire list to file
                break; // stop looping once updated
            }
        }
    }
    
    // call during member registration
    public Member searchMember(String memberId) {
        for (Member m : memberList) {
            if (m.getMemberId().equalsIgnoreCase(memberId)) {
                return m;  // return the member details same row with that particular memberId
            }
        }
        return null; // return null if member not found
    }
    
    public void saveToFile(){
        File outFile = new File("members.txt");
        FileWriter outFileStream = null;
        PrintWriter outStream = null;    
        
        try  {
            outFileStream = new FileWriter(outFile);
            outStream = new PrintWriter(outFileStream);
            for (Member member : memberList) {
                // Format: memberId,username,phoneNo,email,password
                outStream.println(member.getMemberId() + "," + member.getUsername() + "," + member.getPhoneNo() + "," + member.getEmail() + "," + member.getPassword());
            }
        } catch (IOException e) {
            System.out.println("Error saving member data: " + e.getMessage());
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
        File inFile = new File("members.txt");
        
        //create file if not exist
        if (!inFile.exists()) {
            try {
                inFile.createNewFile(); 
            } catch (IOException e) {
                System.out.println("Error creating members.txt file: " + e.getMessage());
            }
        }

        try {
            FileReader inFileReader = new FileReader(inFile);
            BufferedReader inStream = new BufferedReader(inFileReader);
            String str;
            
            while ((str = inStream.readLine()) != null) {
                String[] parts = str.split(",");
                //format: memberId,username,phoneNo,email,password 
                memberList.add(new Member(parts[0], parts[1], parts[2], parts[3], parts[4]));
            }
        } catch (IOException e) {
            System.out.println("Error reading member details on load: " + e.getMessage());
        }
    }
    
    public boolean checkUsernameTaken(String username, String memberId){
        for (Member member : memberList) {
            // member with same username but different member id
            if (member.getUsername().equals(username) && (memberId == null || !member.getMemberId().equals(memberId))) {
                return true; // username is taken
            }
        }
        return false; // username s not taken
    }
    
    public boolean validateLogin(String username, String password) {
        // read every line in the file to to check for matching username and password
        for (Member member : memberList) {
            if (member.getUsername().equals(username)) {
                return member.getPassword().trim().equals(password.trim());
            }
        }
        return false;
    }
    
    public String getMemberId(String username) {
        for (Member member : memberList) {
            if (member.getUsername().trim().equals(username.trim())) {
                return member.getMemberId().trim();
            }
        }
        return null;
    }
    
    // retrieve the profile details that match the row of memberId
    public Member getMemberProfile(String memberId) {
        for (Member member : memberList) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }
}
