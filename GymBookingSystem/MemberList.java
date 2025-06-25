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
        loadFromFile();
    }
    
    public void addMember(Member newMember) {
        memberList.add(newMember);
        saveToFile(); // auto save after adding
    }
    
    public void updateMemberInfo(Member updateMember){
        boolean found = false;
    
        for(int i = 0; i < memberList.size(); i++) {
            if(memberList.get(i).getMemberId().equals(updateMember.getMemberId())) {
                memberList.set(i, updateMember);
                found = true;
                break;
            }
        }
        
        saveToFile();
    }
    
    public Member searchMember(String memberId) {
        for (Member m : memberList) {
            if (m.getMemberId().equalsIgnoreCase(memberId)) {
                return m;  
            }
        }
        return null;
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
        if (!inFile.exists()) {
            try {
                inFile.createNewFile(); //create file if not exist
            } catch (IOException e) {
                System.err.println("Error creating members.data file: " + e.getMessage());
            }
            //return;
        }

        try (BufferedReader inStream = new BufferedReader(new FileReader(inFile))) {
            String str;
            while ((str = inStream.readLine()) != null) {
                String[] parts = str.split(",");
                if (parts.length == 5) { 
                    memberList.add(new Member(parts[0], parts[1], parts[2], parts[3], parts[4]));
                } else {
                    System.err.println("Skipping malformed line in members.data: " + str);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading member data on load: " + e.getMessage());
        }
    }
    
    public boolean validateLogin(String username, String password){
        try (BufferedReader inStream = new BufferedReader(new FileReader("members.txt"))) {
            String str;
            while ((str = inStream.readLine()) != null) {
                String[] parts = str.split(",");
                if (parts.length >= 5 && parts[1].trim().equals(username.trim())) {
                    return parts[4].trim().equals(password.trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Login error: " + e.getMessage());
        }
        return false;
    }
    
    public String getMemberId(String username){
        try (BufferedReader inStream = new BufferedReader(new FileReader("members.txt"))) {
            String str;
            while ((str = inStream.readLine()) != null) {
                String[] parts = str.split(",");
                if (parts.length >= 5 && parts[1].trim().equals(username.trim())) {
                    return parts[0].trim();
                }
            }
        } catch (IOException e) {
            System.err.println("Error finding member ID: " + e.getMessage());
        }
        return null;
    }
    
    public Member getMemberInfo(String memberId){
        try{
            File inFile = new File("members.txt");
            FileReader inFileStream = new FileReader(inFile);
            BufferedReader inStream = new BufferedReader(inFileStream);
            String str;
            while ((str = inStream.readLine()) != null){
                String[] parts = str.split(",");
                if (parts[0].equals(memberId)) {
                    //memberId, username, phoneNo, email, password
                    return new Member(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    
                }
            }
            inStream.close();
        }catch (IOException e) {
            System.out.println("Error reading member data: " + e.getMessage());
        }
        return null;
    }
}
