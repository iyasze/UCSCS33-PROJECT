/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uscs33_project;
import java.util.Scanner;
import java.io.*;
import java.nio.*;

/**
 *
 * @author iyasnaufalnazlim
 */
public class RealTime_User { //this class stores real-time user information

    
    public RealTime_User(boolean guestStatus, String email){
        
        if(guestStatus == false){
            InputStream inputFile = RealTime_User.class.getResourceAsStream("CUSTOMER_DATA.txt");
            
            //InputStream <variable> = <currentFileName>.class.getResourceAsStream(<textFilename>);
            
            if(inputFile == null){
                System.out.println("File not found!");
            }
            
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile));
                //BufferedReader <variable> = new BufferedReader(new InputStreamReader(InputStream variable))
                BufferedWriter writer = new BufferedWriter(new FileWriter("REALTIME_CUSTOMER.txt"));
                // BufferedWriter writer = new BufferedWriter(new fileWriter(<file name>));

                String line;
                while((line = reader.readLine()) != null){
                    if(line.startsWith(email)){

                        String[] foundLine = line.split("\\*\\*\\*");

                        writer.write(foundLine[0] + "\n");
                        writer.write(foundLine[1] + "\n");
                        writer.write(foundLine[2] + "\n");
                        writer.write(foundLine[3] + "\n");
                        writer.write(foundLine[4] + "\n");
                        writer.write(foundLine[5] + "\n");


                    }
                }


            }
            catch(Exception e){
                System.out.println("Message: " + e);
            }
            
            
          
            
        }
        else{ //this will indicate user as guest
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter("REALTIME_CUSTOMER.txt"));
                writer.write("null@gmail.com\n");
                writer.write("GUEST");
            }
            catch(Exception e){
                System.out.println("Message: " + e);
            }
        }
        
    }
}
