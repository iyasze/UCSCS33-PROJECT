/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uscs33_project.event;
import java.util.Scanner;
import java.io.*;
import java.nio.*;
import java.io.File;

//
/**
 *
 * @author iyasnaufalnazlim
 */
public class RealTime_User { //this class stores real-time user information

    
    
    public RealTime_User(boolean guestStatus, String email){
        
        if(guestStatus == true){
           
            
            File file1 = new File("src/uscs33_project/component/CUSTOMER_DATA.txt");
            File file2 = new File("src/uscs33_project/component/REALTIME_CUSTOMER.txt");
            
            if(!file1.exists() && !file2.exists()){
                System.out.println("File not found at: " + file1.getAbsolutePath());
                System.out.println("File not found at: " + file2.getAbsolutePath());
            }
            
            try{
                BufferedReader reader = new BufferedReader(new FileReader(file1));
                //BufferedReader <variable> = new BufferedReader(new InputStreamReader(InputStream variable))
                BufferedWriter writer = new BufferedWriter(new FileWriter(file2));
                // BufferedWriter writer = new BufferedWriter(new fileWriter(<file name>));
                
                System.out.println("FILE INPUT: \n" + file1);
           
                
                String line;
                while((line = reader.readLine()) != null){
                    System.out.println("SEARCHING FOR: " + email);
                    System.out.print(line);
                    
                    String[] splitLine = line.split("\\*\\*\\*");
                    
                    if(email.trim().equals(splitLine[0])){
                        System.out.print("LINE FOUND");
                        String[] foundLine = line.split("\\*\\*\\*");
                        
                        System.out.println("WRITING IN PROCESS...");
                        writer.write(foundLine[0] + System.lineSeparator());
                        writer.write(foundLine[1] + System.lineSeparator());
                        writer.write(foundLine[2] + System.lineSeparator());
                        writer.write(foundLine[3] + System.lineSeparator());
                        writer.write(foundLine[4] + System.lineSeparator());
                        writer.write(foundLine[5] + System.lineSeparator());
                        writer.flush();
                        writer.close();
                        Thread.sleep(1000);
                        System.out.println("WRITING DONE!");
                        
                        break;
                    

                    }
                    else{
                        System.out.print("NOT FOUND");
                    }
                }


            }
            catch(Exception e){
                System.out.println("Message: " + e);
            }
            
            
          
            
        }
        else{ //this will indicate user as guest
            try{
                File file = new File("src/uscs33_project/component/REALTIME_CUSTOMER.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("null@gmail.com\n");
                writer.write("GUEST\n");
                writer.write("-\n");
                writer.write("-\n");
                writer.write("-\n");
                writer.write("-\n");
                writer.flush();
                writer.close();
            }
            catch(Exception e){
                System.out.println("Message: " + e);
            }
        }
        
    }
}
