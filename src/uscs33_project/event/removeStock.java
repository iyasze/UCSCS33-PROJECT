/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uscs33_project.event;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import uscs33_project.model.ModelItemChoice;
import uscs33_project.model.ModelItem;

/**
 *
 * @author iyasnaufalnazlim
 */
public class removeStock {
    public removeStock(ArrayList<ModelItemChoice> items){
        
        Path file = Paths.get("src/uscs33_project/main/products.txt");
        int i = 0;
        
        
        try{
            List<String> fileLines = Files.readAllLines(file);
            List<String> updatedLines = new ArrayList<>();
            for (String line : fileLines) {
                
                if (line.contains("|")) {
                    String temp = line;
                    String[] parts = temp.split("\\|");
                    String productID = parts[0].trim();

                    if (i < items.size() && productID.equals(items.get(i).getItemID())) {
                        // Update stock (current stock + new quantity)
                        try{
                            Double currentStock = Double.parseDouble(parts[1].trim());
                            System.out.println("CURRENT STOCK: " + currentStock);
                            System.out.println("STOCK TO REMOVE: " + items.get(i).getQuantity());

                            Double newStock = currentStock - items.get(i).getQuantity();
                            parts[1] = String.valueOf(newStock);
                            System.out.println("CONTENT: " + parts[1]);
                            updatedLines.add(String.join("|", parts));
                            i++;
                        }
                        catch(NumberFormatException e){
                            System.out.println("CANT UPDATE THE STOCK FOR ITEM: " + items.get(i).getItemID());
                            updatedLines.add(line);
                        }
                        
                    } else {
                        updatedLines.add(line); // No change
                    }                
                } 
                else {
                    updatedLines.add(line); // Header/footer lines
                }
                
                Files.write(file, updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
        }
        }    
        catch(IOException e){
            System.out.println("ERRRORRRRR");
        }
        
        
        }
}
